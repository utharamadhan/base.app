package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTimePlanning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ResearchTimePlanningDAO extends AbstractHibernateDAO<ResearchTimePlanning, Long> implements IResearchTimePlanningDAO {

	@Override
	public ResearchTimePlanning findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchTimePlanning anObject)
			throws SystemException {
		if (anObject.getPkResearchTimePlanning()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchTimePlanning> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchTimePlanning object = ResearchTimePlanning.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchTimePlanning> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchTimePlanning> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
