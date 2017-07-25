package id.base.app.controller.frontEndDisplay;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontEndDisplay.IPagesService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_PAGES)
public class PagesController extends SuperController<Pages>{
	
	protected static Logger LOGGER = LoggerFactory.getLogger(PagesController.class);
	
	@Autowired
	private IPagesService pagesService;
	

	@Override
	public Pages validate(Pages anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Pages.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else if(StringFunction.isEmpty(anObject.getPermalink())){
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = pagesService.getSamePermalink(anObject.getPkPages(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(Pages.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Pages> getMaintenanceService() {
		return pagesService;
	}
	
	@Override
	public Pages preUpdate(Pages anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, Pages newObject) {
		try {
			if(oldObject != null && oldObject instanceof Pages && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (((Pages)oldObject).getImageURL()!=null && !((Pages)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((Pages)oldObject).getImageURL();
					deleteOldImage(oldURL);
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					pagesService.updateThumb(newObject.getPkPages(), thumbURL);
				}else if(StringFunction.isEmpty(newObject.getImageThumbURL())){
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					pagesService.updateThumb(newObject.getPkPages(), thumbURL);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Pages getOldObject(Pages object) throws SystemException {
		Pages oldObject = new Pages();
		return object.getPkPages() != null ? cloneObject(oldObject, findById(object.getPkPages())) : null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public Pages findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return pagesService.findByPermalink(permalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findSimpleData")
	@ResponseBody
	public List<Pages> findSimpleData(@RequestParam(value="type") String type) throws SystemException {
		try {
			return pagesService.findSimpleData(type);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getTocAndPilarLink")
	@ResponseBody
	public List<Pages> getLatestPages() throws SystemException {
		List<String> types = new ArrayList<>();
		types.add(SystemConstant.PagesType.TERM_CONDITION);
		types.add(SystemConstant.PagesType.PILAR);
		return pagesService.getLatestPages(types);
	}
	
	
}