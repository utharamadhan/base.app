package id.base.app.controller.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.contact.IContactSettingService;
import id.base.app.valueobject.contact.ContactSetting;
import id.base.app.valueobject.frontend.HomeSetting;

@RestController
@RequestMapping(RestConstant.RM_CONTACT_SETTING)
public class ContactSettingController extends SuperController<ContactSetting>{
	
	@Autowired
	private IContactSettingService contactSettingService;
	

	@Override
	public ContactSetting validate(ContactSetting anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<ContactSetting> getMaintenanceService() {
		return contactSettingService;
	}
	
	@Override
	public ContactSetting preUpdate(ContactSetting anObject) throws SystemException{
		return validate(anObject);
	}
	
}
