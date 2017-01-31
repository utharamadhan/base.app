package id.base.app.service.research;

import id.base.app.dao.research.IResearchBudgetingDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchBudgeting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchBudgetingService implements IResearchBudgetingService{

	@Autowired
	private IResearchBudgetingDAO researchBudgetingDAO;
	
	@Override
	public ResearchBudgeting findById(Long id) throws SystemException {
		return researchBudgetingDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchBudgeting anObject)
			throws SystemException {
		researchBudgetingDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchBudgetingDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchBudgeting> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchBudgeting> objects = new ArrayList<>();
		ResearchBudgeting object = null;
		for(Long l:objectPKs){
			object = researchBudgetingDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchBudgeting> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchBudgetingDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchBudgeting> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchBudgetingDAO.findAll(filter, order);
	}

}
