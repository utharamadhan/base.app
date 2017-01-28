package id.base.app.dao.contact;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemParameter;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppParameter;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.contact.Contact;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAO extends AbstractHibernateDAO<Contact,Long> implements IContactDAO {

	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Contact obj = findById(objectPK);
			super.delete(obj);
		}
	}
	
	public List<Contact> findAll(){
		return super.findAll();
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
    	 super.update(anObject);
    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	}

	public PagingWrapper<Contact> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Contact findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Contact anObject) throws SystemException {
		if(anObject.getPkContact()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public List<Contact> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Contact> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Contact> getLatestContactUs() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.addOrder(Order.desc("creationTime"));
			crit.createAlias("helpLookup", "help");
			crit.createAlias("temaLookup", "tema");
			crit.setProjection(Projections.projectionList().
					add(Projections.property("name")). //0
					add(Projections.property("email")).
					add(Projections.property("message")).
					add(Projections.property("creationTime")).
					add(Projections.property("help.pkLookup")). //4
					add(Projections.property("help.code")).
					add(Projections.property("help.name")).
					add(Projections.property("tema.pkLookup")). //7
					add(Projections.property("tema.code")).
					add(Projections.property("tema.name")));
			crit.setMaxResults(5);
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Contact c = Contact.getInstance();
					try {
						c.setName(StringFunction.safeStringConvertion(tuple[0]));
						c.setEmail(StringFunction.safeStringConvertion(tuple[1]));
						c.setMessage(StringFunction.safeStringConvertion(tuple[2]));
						BeanUtils.copyProperty(c, "activityDate", tuple[3]);
						if (tuple[4] != null) {
							Lookup lo = new Lookup();
							BeanUtils.copyProperty(lo, "pkLookup", tuple[4]);
							BeanUtils.copyProperty(lo, "code", tuple[5]);
							BeanUtils.copyProperty(lo, "name", tuple[6]);
							BeanUtils.copyProperty(c, "helpLookup", lo);
						}
						if(tuple[7] != null) {
							Lookup lo = new Lookup();
							BeanUtils.copyProperty(lo, "pkLookup", tuple[7]);
							BeanUtils.copyProperty(lo, "code", tuple[8]);
							BeanUtils.copyProperty(lo, "name", tuple[9]);
							BeanUtils.copyProperty(c, "temaLookup", lo);
						}
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					return c;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

	@Override
	public Integer countUnreadMessage() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.add(Restrictions.eq("isRead", Boolean.FALSE));
			crit.setProjection(Projections.rowCount());
		Long rowCount = (Long) crit.uniqueResult();
		return rowCount != null && rowCount > 0L ? rowCount.intValue() : 0;
	}

}