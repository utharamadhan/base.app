package id.base.app.controller.course;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.course.ICourseService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.course.Course;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COURSE)
public class CourseController extends SuperController<Course> {

	@Autowired
	private ICourseService courseService;

	@Override
	public Course validate(Course anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if (StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(Course.CODE,
					messageSource.getMessage("error.mandatory", new String[] { "code" }, Locale.ENGLISH)));
		}
		if (StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(Course.NAME,
					messageSource.getMessage("error.mandatory", new String[] { "name" }, Locale.ENGLISH)));
		}
		if (errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}

	@Override
	public MaintenanceService<Course> getMaintenanceService() {
		return courseService;
	}

	@Override
	public Course preUpdate(Course anObject) throws SystemException {
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, Course newObject) {
		try {
			if(oldObject != null && oldObject instanceof Course && newObject != null && StringFunction.isNotEmpty(newObject.getBasicPictureURL())) {
				if (!((Course)oldObject).getBasicPictureURL().equalsIgnoreCase(newObject.getBasicPictureURL())) {
					String oldURL = ((Course)oldObject).getBasicPictureURL();
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
	public Course getOldObject(Course object) throws SystemException {
		Course oldObject = new Course();
		return object.getPkCourse() != null ? cloneObject(oldObject, findById(object.getPkCourse())) : null;
	}

	@RequestMapping(value = "/findAllCourseCodeName")
	@ResponseBody
	public List<Course> findAllCourseCodeName() throws SystemException {
		return courseService.findAllCourseCodeName();
	}

	@RequestMapping(value = "/findAllCourseAndTags")
	@ResponseBody
	public List<Course> findAllCourseAndTags(@RequestParam Map<String, Object> paramWrapper) throws SystemException {
		return courseService.findAllCourseAndTags(paramWrapper);
	}

	@RequestMapping(value = "/findAllCourseAndTagsPaging")
	@ResponseBody
	public PagingWrapper<Course> findAllCourseAndTagsPaging(
			@RequestParam(value = "startNo", defaultValue = "1") int startNo,
			@RequestParam(value = "offset", defaultValue = "10") int offset,
			@RequestParam Map<String, Object> paramWrapper) throws SystemException {
		PagingWrapper<Course> pw = new PagingWrapper<Course>();
		pw = courseService.findAllCourseAndTags(startNo, offset, paramWrapper);
		return pw;
	}

}
