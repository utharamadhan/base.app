package id.base.app.controller.aboutUs;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.advisory.ICategoryService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.advisory.Category;

@RestController
@RequestMapping(RestConstant.RM_ADVISORY_CATEGORY)
public class CategoryController extends SuperController<Category>{
	
	@Autowired
	private ICategoryService categoryService;
	

	@Override
	public Category validate(Category anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(Tutor.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
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
		anObject.setValid(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
