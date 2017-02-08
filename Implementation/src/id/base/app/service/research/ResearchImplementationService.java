package id.base.app.service.research;

import id.base.app.dao.research.IResearchImplementationDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchImplementation;

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
public class ResearchImplementationService extends QueryTransformer<ResearchImplementation> implements IResearchImplementationService{

	@Autowired
	private IResearchImplementationDAO researchImplementationDAO;
	
	@Override
	public ResearchImplementation findById(Long id) throws SystemException {
		return researchImplementationDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchImplementation anObject)
			throws SystemException {
		researchImplementationDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchImplementationDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchImplementation> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchImplementation> objects = new ArrayList<>();
		ResearchImplementation object = null;
		for(Long l:objectPKs){
			object = researchImplementationDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchImplementation> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchImplementationDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchImplementation> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchImplementationDAO.findAll(filter, order);
	}
	
	@Override
	public List<ResearchImplementation> findByFkResearch(Long fkResearch) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(ResearchImplementation.FK_RESEARCH, fkResearch));
		Order order = new Order();
		order.add(Order.asc(ResearchImplementation.STEP_NO));
		DetachedCriteria detachedCriteria = criteriaByProperty(ResearchImplementation.MAINTENANCE_LIST_FIELDS, exp, order);
		return researchImplementationDAO.loadAll(detachedCriteria);
	}
}