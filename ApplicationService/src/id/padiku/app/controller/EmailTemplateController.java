package id.padiku.app.controller;

import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.email.EmailTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_EMAIL_TEMPLATE)
public class EmailTemplateController extends SuperController<EmailTemplate>{
	
	@Autowired
	private MaintenanceService<EmailTemplate> maintenanceService;
	
	@Override
	public MaintenanceService<EmailTemplate> getMaintenanceService() {
		return this.maintenanceService;
	}

	@Override
	public EmailTemplate validate(EmailTemplate anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getCode() == null){
			errors.add(new ErrorHolder("Name is Mandatory"));
		}
		if(anObject.getTemplate() == null){
			errors.add(new ErrorHolder("Content is Mandatory"));
		}
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
}
