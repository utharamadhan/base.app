package id.base.app.controller.publication;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.news.INewsService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.News;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
					String fileSystemURL = SystemConstant.FILE_STORAGE + oldURL.substring(SystemConstant.IMAGE_SHARING_URL.length(), oldURL.length());
					Path path = Paths.get(fileSystemURL);
					Files.deleteIfExists(path);
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
