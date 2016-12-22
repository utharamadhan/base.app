package id.base.app.controller.course;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.course.IGroupCourseService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.course.GroupCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_GROUP_COURSE)
public class GroupCourseController extends SuperController<GroupCourse>{
	
	@Autowired
	private IGroupCourseService groupCourseService;
	

	@Override
	public GroupCourse validate(GroupCourse anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(GroupCourse.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(GroupCourse.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getShortDescription())) {
			errorList.add(new ErrorHolder(GroupCourse.SHORT_DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"short description"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getBasicPictureURL())) {
			errorList.add(new ErrorHolder(GroupCourse.BASIC_PICTURE_URL, messageSource.getMessage("error.mandatory", new String[]{"picture"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getFullDescription())) {
			errorList.add(new ErrorHolder(GroupCourse.FULL_DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"full description"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<GroupCourse> getMaintenanceService() {
		return groupCourseService;
	}
	
	@Override
	public GroupCourse preUpdate(GroupCourse anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
