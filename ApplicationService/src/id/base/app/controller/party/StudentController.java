package id.base.app.controller.party;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.party.IStudentService;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;
import id.base.app.valueobject.program.StudentCourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

@RestController
@RequestMapping(value=RestConstant.RM_STUDENT)
public class StudentController extends SuperController<Student> {
	
	public StudentController() {
		super();
	}
	
	@Autowired
	private ILookupService lookupService;
	@Autowired
	private IStudentService studentService;

	@Override
	public MaintenanceService<Student> getMaintenanceService() {
		return studentService;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateReturn")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public Long updateReturn(@RequestBody @Validated(UpdateEntity.class) Student student, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    getMaintenanceService().saveOrUpdate(preUpdate(student));
	    return student.getPkStudent();
	}
	
	@Override
	public Student preUpdate(Student anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}

	@Override
	public Student validate(Student anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if (StringFunction.isNotEmpty(anObject.getValidationType())) {
			if (anObject.getValidationType().equals("basicInfo")) {
				if(StringFunction.isEmpty(anObject.getName())){
					errorList.add(new ErrorHolder(Student.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
				}
				if(anObject.getBirthDate() == null){
					errorList.add(new ErrorHolder(Student.BIRTH_DATE, messageSource.getMessage("error.mandatory", new String[]{"birth date"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getBirthPlace())){
					errorList.add(new ErrorHolder(Student.BIRTH_PLACE, messageSource.getMessage("error.mandatory", new String[]{"birth place"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getPhoneNumber())){
					errorList.add(new ErrorHolder(Student.PHONE_NUMBER, messageSource.getMessage("error.mandatory", new String[]{"no. telp"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getEmail())){
					errorList.add(new ErrorHolder(Student.EMAIL, messageSource.getMessage("error.mandatory", new String[]{"email"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getAddress())){
					errorList.add(new ErrorHolder(Student.ADDRESS, messageSource.getMessage("error.mandatory", new String[]{"email"}, Locale.ENGLISH)));
				}
			}
			if (anObject.getValidationType().equals("latestWork")) {
				if(StringFunction.isEmpty(anObject.getCompany())) {
					errorList.add(new ErrorHolder(Student.COMPANY, messageSource.getMessage("error.mandatory", new String[]{"company"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getCompanyPosition())) {
					errorList.add(new ErrorHolder(Student.COMPANY_POSITION, messageSource.getMessage("error.mandatory", new String[]{"position"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getCompanyCity())) {
					errorList.add(new ErrorHolder(Student.COMPANY_CITY, messageSource.getMessage("error.mandatory", new String[]{"city"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getCompanyDescription())) {
					errorList.add(new ErrorHolder(Student.COMPANY_DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
				}
			}
			if (anObject.getValidationType().equals("latestEducation")) {
				if(StringFunction.isEmpty(anObject.getSchool())) {
					errorList.add(new ErrorHolder(Student.SCHOOL, messageSource.getMessage("error.mandatory", new String[]{"school"}, Locale.ENGLISH)));
				}
				if(anObject.getSchoolDatesAttended() == null) {
					errorList.add(new ErrorHolder(Student.SCHOOL_DATES_ATTENDED, messageSource.getMessage("error.mandatory", new String[]{"dates attended"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getSchoolDegree())) {
					errorList.add(new ErrorHolder(Student.SCHOOL_DEGREE, messageSource.getMessage("error.mandatory", new String[]{"degree"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getSchoolFieldOfStudy())) {
					errorList.add(new ErrorHolder(Student.SCHOOL_FIELD_OF_STUDY, messageSource.getMessage("error.mandatory", new String[]{"field of study"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getSchoolGrade())) {
					errorList.add(new ErrorHolder(Student.SCHOOL_GRADE, messageSource.getMessage("error.mandatory", new String[]{"grade"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getSchoolActivitiesSocieties())) {
					errorList.add(new ErrorHolder(Student.SCHOOL_ACTIVITIES_SOCIETIES, messageSource.getMessage("error.mandatory", new String[]{"Activities Societies"}, Locale.ENGLISH)));
				}
				if(StringFunction.isEmpty(anObject.getSchoolDescription())) {
					errorList.add(new ErrorHolder(Student.SCHOOL_DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
				}
			}
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/enrollCourse")
	@ResponseStatus( HttpStatus.OK )
	public void enrollCourse(@RequestBody StudentCourse anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    studentService.enrollCourse(preEnrollCourse(anObject));
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/processLearning")
	@ResponseStatus( HttpStatus.OK )
	public void processLearning(@RequestBody StudentCourse anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    studentService.processLearning(preProcessLearning(anObject));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getListByFilter")
	@ResponseBody
	public PagingWrapper<VWStudentList> getListByFilter(
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson)
			throws SystemException {
		PagingWrapper<VWStudentList> pw = new PagingWrapper<>();
		try {
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			if(StringUtils.isNotEmpty(filterJson)){
				filter = prepareFilter((List<SearchFilter>)mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){}));
			}
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			if(StringUtils.isNotEmpty(orderJson)){
				order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			}
			pw = studentService.getListByFilter(startNo, offset, filter, order);
			LOGGER.debug(mapper.writeValueAsString(pw));
			return pw;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("error finding your data",e);
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	public List<SearchFilter> prepareFilter(List<SearchFilter> filter) {
		if(filter != null) {
			for(SearchFilter f : filter) {
				if(f.getFieldName().equals(VWStudentList.ENROLL_DATE) && f.getValue() != null) {
					f.setValue(DateTimeFunction.string2Date(f.getValue().toString(), SystemConstant.DATABASE_DATE_FORMAT_STD));
				}
			}
		}
		return filter;
	}
	
	public StudentCourse preEnrollCourse(StudentCourse anObject) throws SystemException{
			anObject.setStatus(SystemConstant.ValidFlag.VALID);
			anObject.setEnrollDate(DateTimeFunction.truncateDate(DateTimeFunction.getCalendar().getTime()));
			anObject.setStudentCourseStatusLookup(lookupService.findByCode(ILookupConstant.EnrollmentStatus.ENROLLED, ILookupGroupConstant.ENROLLMENT_STATUS));
		return validateEnrollCourse(anObject);
	}

	public StudentCourse validateEnrollCourse(StudentCourse anObject) throws SystemException {
		return anObject;
	}
	
	public StudentCourse preProcessLearning(StudentCourse anObject) throws SystemException {
		StudentCourse objDb = studentService.findStudentCourseById(anObject.getPkStudentCourse());
			objDb.setActionType(anObject.getActionType());
		return validateProcessLearning(objDb);
	}
	
	public StudentCourse validateProcessLearning(StudentCourse anObject) throws SystemException {
		return anObject;
	}
	
}