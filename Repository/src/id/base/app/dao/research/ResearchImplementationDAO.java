package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ResearchImplementationDAO extends AbstractHibernateDAO<ResearchImplementation, Long> implements IResearchImplementationDAO {

	@Override
	public ResearchImplementation findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchImplementation anObject) throws SystemException {
		if (anObject.getPkResearchImplementation()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchImplementation> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchImplementation object = ResearchImplementation.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchImplementation> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchImplementation> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
