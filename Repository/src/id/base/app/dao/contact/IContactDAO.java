package id.base.app.dao.contact;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.contact.Contact;

import java.util.List;

public interface IContactDAO extends IBaseDAO<Contact> {
	
	public Integer countUnreadMessage() throws SystemException;
	
	public List<Contact> getLatestContactUs() throws SystemException;
	
}
