package id.base.app.controller.aboutUs;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.aboutUs.IEngagementService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Engagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_ENGAGEMENT)
public class EngagementController extends SuperController<Engagement>{
	
	@Autowired
	private IEngagementService engagementService;
	

	@Override
	public Engagement validate(Engagement anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Engagement.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(Engagement.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Engagement> getMaintenanceService() {
		return engagementService;
	}
	
	@Override
	public Engagement preUpdate(Engagement anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}