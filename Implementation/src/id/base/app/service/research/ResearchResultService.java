package id.base.app.service.research;

import id.base.app.dao.research.IResearchOfficerDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchOfficer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;
import softtech.hong.hce.model.Order;

@Service
@Transactional
public class ResearchResultService extends QueryTransformer<ResearchOfficer> implements IResearchOfficerService{

	@Autowired
	private IResearchOfficerDAO researchOfficerDAO;
	
	@Override
	public ResearchOfficer findById(Long id) throws SystemException {
		return researchOfficerDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchOfficer anObject)
			throws SystemException {
		researchOfficerDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchOfficerDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchOfficer> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchOfficer> objects = new ArrayList<>();
		ResearchOfficer object = null;
		for(Long l:objectPKs){
			object = researchOfficerDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchOfficer> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchOfficerDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchOfficer> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchOfficerDAO.findAll(filter, order);
	}
	
	@Override
	public List<ResearchOfficer> findByFkResearch(Long fkResearch) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(ResearchOfficer.FK_RESEARCH, fkResearch));
		Order order = new Order();
		order.add(Order.asc(ResearchOfficer.PK_RESEARCH_OFFICER));
		DetachedCriteria detachedCriteria = criteriaByProperty(ResearchOfficer.MAINTENANCE_LIST_FIELDS, exp, order);
		return researchOfficerDAO.loadAll(detachedCriteria);
	}
}