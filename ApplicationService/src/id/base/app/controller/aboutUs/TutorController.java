package id.base.app.controller.aboutUs;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.aboutUs.ITutorService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Tutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_TUTOR)
public class TutorController extends SuperController<Tutor>{
	
	@Autowired
	private ITutorService tutorService;
	

	@Override
	public Tutor validate(Tutor anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(Tutor.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getProfileDescription())) {
			errorList.add(new ErrorHolder(Tutor.PROFILE_DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"profile description"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Tutor> getMaintenanceService() {
		return tutorService;
	}
	
	@Override
	public Tutor preUpdate(Tutor anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
	@RequestMapping(value="/findAllTutorCodeAndName")
	@ResponseBody
	public List<Tutor> findAllTutorCodeAndName() throws SystemException {
		return tutorService.findAllTutorCodeAndName();
	}
	
}
