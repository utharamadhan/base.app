package id.padiku.app.dao.email;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.email.EmailTemplate;

public interface IEmailTemplateDAO extends IBaseDAO<EmailTemplate> {
	
	public EmailTemplate findByCode(String templateCode) throws SystemException;

}
