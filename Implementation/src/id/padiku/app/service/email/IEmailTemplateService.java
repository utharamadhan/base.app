package id.padiku.app.service.email;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.email.EmailTemplate;

public interface IEmailTemplateService extends MaintenanceService<EmailTemplate>{
	
	public EmailTemplate findByCode(String templateCode) throws SystemException;

}
