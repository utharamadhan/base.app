package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.advisory.ICategoryService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}