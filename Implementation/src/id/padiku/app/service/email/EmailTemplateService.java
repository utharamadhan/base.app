package id.padiku.app.service.email;

import id.padiku.app.dao.email.IEmailTemplateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.email.EmailTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailTemplateService implements IEmailTemplateService {

	@Autowired
	private IEmailTemplateDAO templateDAO;
	
	@Override
	public EmailTemplate findById(Long id) throws SystemException {
		return templateDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(EmailTemplate anObject) throws SystemException {
		templateDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		templateDAO.delete(objectPKs);
	}

	@Override
	public List<EmailTemplate> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<EmailTemplate> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return templateDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<EmailTemplate> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public EmailTemplate findByCode(String templateCode) throws SystemException {
		return templateDAO.findByCode(templateCode);
	}

}
