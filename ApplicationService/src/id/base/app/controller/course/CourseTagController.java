package id.base.app.controller.course;

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
import id.base.app.service.course.ICourseTagService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.course.Tag;

@RestController
@RequestMapping(RestConstant.RM_COURSE_TAG)
public class CourseTagController extends SuperController<Tag>{
	
	@Autowired
	private ICourseTagService courseTagService;
	

	@Override
	public Tag validate(Tag anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(Tag.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(Tag.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Tag> getMaintenanceService() {
		return courseTagService;
	}
	
	@Override
	public Tag preUpdate(Tag anObject) throws SystemException{
		anObject.setValid(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
