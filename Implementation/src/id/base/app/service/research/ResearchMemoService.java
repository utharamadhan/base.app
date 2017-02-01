package id.base.app.service.research;

import id.base.app.dao.research.IResearchMemoDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchMemo;

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
public class ResearchMemoService extends QueryTransformer<ResearchMemo> implements IResearchMemoService{

	@Autowired
	private IResearchMemoDAO researchMemoDAO;

	@Override
	public ResearchMemo findById(Long id) throws SystemException {
		return researchMemoDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchMemo anObject)
			throws SystemException {
		researchMemoDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchMemoDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchMemo> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchMemo> objects = new ArrayList<>();
		ResearchMemo object = null;
		for(Long l:objectPKs){
			object = researchMemoDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchMemo> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchMemoDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchMemo> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchMemoDAO.findAll(filter, order);
	}
	
	@Override
	public List<ResearchMemo> findMemoByFkResearch(Long fkResearch) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(ResearchMemo.FK_RESEARCH, fkResearch));
		Order order = new Order();
		order.add(Order.asc(ResearchMemo.PK_RESEARCH_MEMO));
		DetachedCriteria detachedCriteria = criteriaByProperty(ResearchMemo.MAINTENANCE_LIST_FIELDS, exp, order);
		return researchMemoDAO.loadAll(detachedCriteria);
	}
	
}