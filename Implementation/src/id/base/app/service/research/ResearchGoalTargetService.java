package id.base.app.service.research;

import id.base.app.dao.research.IResearchGoalTargetDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchGoalTarget;

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
public class ResearchGoalTargetService extends QueryTransformer<ResearchGoalTarget> implements IResearchGoalTargetService{

	@Autowired
	private IResearchGoalTargetDAO researchGoalTargetDAO;

	@Override
	public ResearchGoalTarget findById(Long id) throws SystemException {
		return researchGoalTargetDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchGoalTarget anObject)
			throws SystemException {
		researchGoalTargetDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchGoalTargetDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchGoalTarget> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchGoalTarget> objects = new ArrayList<>();
		ResearchGoalTarget object = null;
		for(Long l:objectPKs){
			object = researchGoalTargetDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchGoalTarget> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchGoalTargetDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchGoalTarget> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchGoalTargetDAO.findAll(filter, order);
	}

	@Override
	public List<ResearchGoalTarget> findGoalTargetByFkResearch(Long fkResearch) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(ResearchGoalTarget.FK_RESEARCH, fkResearch));
		Order order = new Order();
		order.add(Order.asc(ResearchGoalTarget.PK_RESEARCH_GOAL_TARGET));
		DetachedCriteria detachedCriteria = criteriaByProperty(ResearchGoalTarget.MAINTENANCE_LIST_FIELDS, exp, order);
		return researchGoalTargetDAO.loadAll(detachedCriteria);
	}
}
