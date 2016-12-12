package id.padiku.app.service.lookup;

import id.padiku.app.dao.lookup.ILookupGroupDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupGroup;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class LookupGroupService extends QueryTransformer<LookupGroup> implements MaintenanceService<LookupGroup> , ILookupGroupService{

	@Autowired
	protected  ILookupGroupDAO lookupGroupDAO;
	
	public LookupGroupService() {
		super();
	}

	public LookupGroupService(ILookupGroupDAO lookupGroupDAO){
		this.lookupGroupDAO = lookupGroupDAO;
	}

	public LookupGroup findById(Long id) throws SystemException {
		return null;
	}

	public void saveOrUpdate(LookupGroup anObject) throws SystemException {
	}

	public void delete(Long[] objectPKs) throws SystemException {
	}

	public Long create(LookupGroup objectToCreate) {
		return null;
	}

	public LookupGroup findByReferencesId(Long referencesPK)
			throws SystemException {
		return null;
	}

	public PagingWrapper<LookupGroup> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		if (order==null) {
			order = new ArrayList<SearchOrder>();
            order.add(new SearchOrder(LookupGroup.NAME, SearchOrder.Sort.ASC));
		}
		return lookupGroupDAO.findAllLookupGroup(startNo, offset, filter, order);
	}

	@Override
	public List<LookupGroup> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public List<LookupGroup> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public LookupGroup findByName(String name) throws SystemException {
		return lookupGroupDAO.findByName(name);
	}
	
	@Override
	public boolean checkUpdatableByGroupName(String name) throws SystemException {
		return lookupGroupDAO.checkUpdatableByGroupName(name);
	}
	
	@Override
	public boolean checkUpdatableByLookupPK(Long pk) throws SystemException {
		return lookupGroupDAO.checkUpdatableByLookupPK(pk);
	}
	
	@Override
	public List<LookupGroup> findLookupGroupForCompany() throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(LookupGroup.IS_COMPANY, Boolean.TRUE));
		DetachedCriteria detachedCriteria = criteriaByProperty(new String[]{LookupGroup.NAME}, exp);
		return lookupGroupDAO.loadAll(detachedCriteria);
	}
	
}
