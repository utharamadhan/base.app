package id.base.app.controller.publication;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.news.INewsService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_NEWS)
public class NewsController extends SuperController<News>{
	
	@Autowired
	private INewsService newsService;
	
	@Override
	public News validate(News anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(News.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = newsService.getSamePermalink(anObject.getPkNews(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(News.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getExcerpt())) {
			errorList.add(new ErrorHolder(News.EXCERPT, messageSource.getMessage("error.mandatory", new String[]{"excerpt"}, Locale.ENGLISH)));
		}
		if(!errorList.isEmpty()) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<News> getMaintenanceService() {
		return newsService;
	}
	
	@Override
	public News preUpdate(News anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, News newObject) {
		try {
			if(oldObject != null && oldObject instanceof News && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (!((News)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((News)oldObject).getImageURL();
					deleteOldImage(oldURL);
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					newsService.updateThumb(newObject.getPkNews(), thumbURL);
				}else if(StringFunction.isEmpty(newObject.getImageThumbURL())){
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					newsService.updateThumb(newObject.getPkNews(), thumbURL);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public News getOldObject(News object) throws SystemException {
		News oldObject = new News();
		return object.getPkNews() != null ? cloneObject(oldObject, findById(object.getPkNews())) : null;
	}
	
	@Override
	@RequestMapping(method=RequestMethod.DELETE, value="/delete")
	@ResponseStatus( HttpStatus.OK )
	public void delete(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		List<String> imageURLs = newsService.findThumbById(objectPKs);
		getMaintenanceService().delete(preDelete(objectPKs));
		for (String oldURL : imageURLs) {
			try {
				deleteOldImage(oldURL);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatestNews")
	@ResponseBody
	public List<News> findLatestNews(@RequestParam(value="number") int number) throws SystemException {
		try {
			return newsService.findLatestNews(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public News findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return newsService.findByPermalink(permalink);
	}
	
}
