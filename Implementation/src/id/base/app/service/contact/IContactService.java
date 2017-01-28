package id.base.app.service.contact;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.contact.Contact;

import java.util.List;

public interface IContactService extends MaintenanceService<Contact>{

	public Integer countUnreadMessage() throws SystemException;
	
	public List<Contact> getLatestContactUs() throws SystemException;
	
}
