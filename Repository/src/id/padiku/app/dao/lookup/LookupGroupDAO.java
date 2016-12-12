package id.padiku.app.dao.lookup;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.LookupGroup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

@Repository
public class LookupGroupDAO extends AbstractHibernateDAO<LookupGroup,Long> implements ILookupGroupDAO {

	public PagingWrapper<LookupGroup> findAllLookupGroup(int startNo, int offset)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset);
	}

	@Override
	public PagingWrapper<LookupGroup> findAllLookupGroup(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public LookupGroup findByName(String name) throws SystemException {
		Criteria codeCriteria = getSession().createCriteria(domainClass);
		codeCriteria.add(Restrictions.eq(LookupGroup.NAME, name));
		return (LookupGroup) codeCriteria.uniqueResult();
	}
	
	@Override
	public boolean checkUpdatableByGroupName(String name) throws SystemException {
		Criteria checkCriteria = getSession().createCriteria(domainClass);
		checkCriteria.add(Restrictions.eq(LookupGroup.NAME, name));
		checkCriteria.setProjection(Projections.property(LookupGroup.IS_UPDATABLE));
		Boolean updatable = (Boolean)checkCriteria.uniqueResult();
		return updatable==null?false:updatable.booleanValue();
	}
	
	@Override
	public boolean checkUpdatableByLookupPK(Long pk) throws SystemException {
		DetachedCriteria lookupCriteria = DetachedCriteria.forClass(Lookup.class);
		lookupCriteria.add(Restrictions.eq(Lookup.ID, pk));
		lookupCriteria.setProjection(Projections.property(Lookup.LOOKUP_GROUP_STRING));
		Criteria checkCriteria = getSession().createCriteria(domainClass);
		checkCriteria.add(Subqueries.propertyEq(LookupGroup.NAME, lookupCriteria));
		checkCriteria.setProjection(Projections.property(LookupGroup.IS_UPDATABLE));
		Boolean updatable = (Boolean)checkCriteria.uniqueResult();
		return updatable==null?false:updatable.booleanValue();
	}

	@Override
	public LookupGroup findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(LookupGroup anObject) throws SystemException {
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		
	}

	@Override
	public List<LookupGroup> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<LookupGroup> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}
}