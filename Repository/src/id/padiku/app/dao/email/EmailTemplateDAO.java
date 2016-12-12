package id.padiku.app.dao.email;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.email.EmailTemplate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmailTemplateDAO extends AbstractHibernateDAO<EmailTemplate, Long> implements IEmailTemplateDAO {

	@Override
	public EmailTemplate findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(EmailTemplate anObject) throws SystemException {
		if(anObject.getPkEmailTemplate()==null){			
			super.create(anObject);
		} else {
			super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			EmailTemplate obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<EmailTemplate> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<EmailTemplate> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public EmailTemplate findByCode(String templateCode) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.add(Restrictions.eq("code", templateCode));
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.setMaxResults(1);
		return (EmailTemplate) crit.uniqueResult();
	}
	
}