package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchKeyword;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ResearchKeywordDAO extends AbstractHibernateDAO<ResearchKeyword, Long> implements IResearchKeywordDAO {

	@Override
	public ResearchKeyword findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchKeyword anObject) throws SystemException {
		if (anObject.getPkResearchKeyword()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchKeyword> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchKeyword object = ResearchKeyword.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchKeyword> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchKeyword> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return null;
	}
}