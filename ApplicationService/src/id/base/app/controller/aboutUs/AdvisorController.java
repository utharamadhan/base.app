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
import id.base.app.service.advisory.IAdvisorService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.advisory.Advisor;

@RestController
@RequestMapping(RestConstant.RM_ADVISOR)
public class AdvisorController extends SuperController<Advisor>{
	
	@Autowired
	private IAdvisorService advisorService;
	

	@Override
	public Advisor validate(Advisor anObject) throws SystemException {
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
	public MaintenanceService<Advisor> getMaintenanceService() {
		return advisorService;
	}
	
	@Override
	public Advisor preUpdate(Advisor anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
