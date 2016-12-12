package id.padiku.app.dao.master;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyLookupDAO extends AbstractHibernateDAO<CompanyLookup,Long> implements ICompanyLookupDAO {

	private Order getDefaultOrder() {
		return Order.asc(Lookup.ORDER_NO_STRING);
	}
	
	private Object getDefaultProjections() {
		return Projections.projectionList().
				add(Projections.property(CompanyLookup.ID)).
				add(Projections.property(CompanyLookup.COMPANY_ID)).
				add(Projections.property(CompanyLookup.LOOKUP_GROUP_STRING)).
				add(Projections.property(CompanyLookup.CODE)).
				add(Projections.property(CompanyLookup.NAME_ID)).
				add(Projections.property(CompanyLookup.NAME_EN)).
				add(Projections.property(CompanyLookup.ORDER_NO)).
				add(Projections.property(CompanyLookup.STATUS));
	}
	
	class LookupTransformer implements ResultTransformer {
		@Override
		public Object transformTuple(Object[] tuple, String[] aliases) {
			CompanyLookup obj = new CompanyLookup();
			try{
				obj.setPkCompanyLookup(Long.valueOf(tuple[0].toString()));
				if(tuple[1]!=null) {
					Company comp = Company.getInstance(Long.valueOf(tuple[1].toString()));
					obj.setCompany(comp);
				}
				obj.setLookupGroupString(tuple[2].toString());
				BeanUtils.copyProperty(obj, CompanyLookup.CODE, tuple[3]);
				BeanUtils.copyProperty(obj, CompanyLookup.NAME_ID, tuple[4]);
				BeanUtils.copyProperty(obj, CompanyLookup.NAME_EN, tuple[5]);
				BeanUtils.copyProperty(obj, CompanyLookup.ORDER_NO, tuple[6]);
				BeanUtils.copyProperty(obj, CompanyLookup.STATUS, tuple[7]);
			}catch(Exception e){
				LOGGER.error(e.getMessage(), e);
			}
			return obj;
		}

		@Override
		public List transformList(List collection) {
			return collection;
		}		
	}
	
	@Override
	public CompanyLookup findById(Long companyLookupId) throws SystemException {
		return super.findByPK(companyLookupId);
	}

	@Override
	public void saveOrUpdate(CompanyLookup obj) throws SystemException {
		if (obj.getPkCompanyLookup()==null) {
			super.create(obj);
		} else {
		    super.update(obj);
		}
	}
	
	@Override
	public List<CompanyLookup> findAll(List<SearchFilter> filters, List<SearchOrder> orders) throws SystemException {
		return super.findAll(filters, orders, null);
	}
	
	@Override
	public void delete(Long[] ids) throws SystemException {
		List<CompanyLookup> objectList = new ArrayList<>();
		for(int i=0;i<ids.length;i++){
			CompanyLookup object = new CompanyLookup();
			object = findById(ids[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<CompanyLookup> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyLookup> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyLookup> findLookupByLookupGroup(Long pkCompany, String lookupGroup) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias(CompanyLookup.COMPANY, CompanyLookup.COMPANY);
			crit.add(Restrictions.eq(CompanyLookup.COMPANY_ID, pkCompany));
			crit.add(Restrictions.eq(CompanyLookup.LOOKUP_GROUP_STRING, lookupGroup));
			crit.add(Restrictions.eq(CompanyLookup.STATUS, SystemConstant.ValidFlag.VALID));
			crit.addOrder(getDefaultOrder());
			crit.setProjection((ProjectionList) getDefaultProjections());
			crit.setCacheable(true);
			crit.setCacheRegion("query.findLookupByLookupGroup");
			crit.setResultTransformer(new LookupTransformer());
		return crit.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyLookup> findLookupByLookupGroupOrderBy(Long pkCompany, String lookupGroup, String orderBy, boolean desc) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias(CompanyLookup.COMPANY, CompanyLookup.COMPANY);
			crit.add(Restrictions.eq(CompanyLookup.COMPANY_ID, pkCompany));
			crit.add(Restrictions.eq(CompanyLookup.LOOKUP_GROUP_STRING, lookupGroup));
			crit.add(Restrictions.eq(CompanyLookup.STATUS, SystemConstant.ValidFlag.VALID));
			if(desc){
				crit.addOrder(Order.desc(orderBy));			
			}else{
				crit.addOrder(Order.asc(orderBy));
			}
			crit.setProjection((ProjectionList) getDefaultProjections());
			crit.setResultTransformer(new LookupTransformer());
		return crit.list();
	}

	@Override
	public CompanyLookup findByCode(Long pkCompany, String code, String lookupGroup) {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias(CompanyLookup.COMPANY, CompanyLookup.COMPANY);
			crit.add(Restrictions.eq(CompanyLookup.COMPANY_ID, pkCompany));
			crit.add(Restrictions.eq(CompanyLookup.CODE, code));
			crit.add(Restrictions.eq(CompanyLookup.LOOKUP_GROUP_STRING, lookupGroup));
			crit.add(Restrictions.eq(CompanyLookup.STATUS, SystemConstant.ValidFlag.VALID));
			crit.setProjection((ProjectionList) getDefaultProjections());
			crit.setResultTransformer(new LookupTransformer());
		return (CompanyLookup) crit.uniqueResult();
	}

	@Override
	public Boolean isAlreadyExist(Long pkCompany, String lookupGroup, String code, Long orderNo, Long pkCompanyLookup) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias(CompanyLookup.COMPANY, CompanyLookup.COMPANY);
			if(pkCompanyLookup != null){
				crit.add(Restrictions.ne(CompanyLookup.ID, pkCompanyLookup));	
			}
			crit.add(Restrictions.eq(CompanyLookup.COMPANY_ID, pkCompany));
			crit.add(Restrictions.eq(CompanyLookup.CODE, code));
			crit.add(Restrictions.eq(CompanyLookup.LOOKUP_GROUP_STRING, lookupGroup));
			if(orderNo != null) {
				crit.add(Restrictions.eq(CompanyLookup.ORDER_NO, orderNo));
			}
			crit.add(Restrictions.eq(CompanyLookup.STATUS, SystemConstant.ValidFlag.VALID));
			crit.setProjection(Projections.rowCount());
			Long rowCount = (Long) crit.uniqueResult();
		return rowCount != null && rowCount > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
}
