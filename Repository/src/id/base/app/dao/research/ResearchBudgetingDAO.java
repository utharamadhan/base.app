package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchBudgeting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ResearchBudgetingDAO extends AbstractHibernateDAO<ResearchBudgeting, Long> implements IResearchBudgetingDAO {

	@Override
	public ResearchBudgeting findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchBudgeting anObject) throws SystemException {
		if (anObject.getPkResearchBudgeting()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchBudgeting> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchBudgeting object = ResearchBudgeting.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchBudgeting> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchBudgeting> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
