package id.base.app.controller;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.advisory.ICategoryService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_CATEGORY)
public class CategoryController extends SuperController<Category>{
	
	@Autowired
	private ICategoryService categoryService;

	@Override
	public Category validate(Category anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getType())) {
			errorList.add(new ErrorHolder(Category.TYPE, messageSource.getMessage("error.mandatory", new String[]{"type"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Category.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = categoryService.getSamePermalink(anObject.getPkCategory(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Category> getMaintenanceService() {
		return categoryService;
	}
	
	@Override
	public Category preUpdate(Category anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, Category newObject) {
		try {
			if(oldObject != null && oldObject instanceof Category && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (!((Category)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((Category)oldObject).getImageURL();
					deleteOldImage(oldURL);
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					categoryService.updateThumb(newObject.getPkCategory(), thumbURL);
				}else if(StringFunction.isEmpty(newObject.getImageThumbURL())){
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					categoryService.updateThumb(newObject.getPkCategory(), thumbURL);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void postDelete(Category oldObject) {
		try {
			if(oldObject!=null && StringFunction.isNotEmpty(oldObject.getImageURL())) {
				deleteOldImage(oldObject.getImageURL());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Category getOldObject(Category object) throws SystemException {
		Category oldObject = new Category();
		return object.getPkCategory() != null ? cloneObject(oldObject, findById(object.getPkCategory())) : null;
	}
	
	@RequestMapping(value="/findByType/{type}")
	@ResponseBody
	public List<Category> findByType(@PathVariable(value="type") String type) throws SystemException {
		return categoryService.findByType(type);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getFirstPermalinkData/{type}")
	@ResponseBody
	private String getFirstPermalinkData(@PathVariable("type") String type){
		return categoryService.getFirstPermalinkData(type);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findSimpleDataByPermalink/{permalink}")
	@ResponseBody
	private Category findSimpleDataByPermalink(@PathVariable("permalink") String permalink){
		return categoryService.findSimpleDataByPermalink(permalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findSimpleDataForList/{type}")
	@ResponseBody
	private List<Category> findSimpleDataForList(@PathVariable("type") String type){
		return categoryService.findSimpleDataForList(type);
	}
}