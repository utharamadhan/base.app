package id.base.app.service.research;

import id.base.app.dao.research.IResearchKeywordDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchKeyword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchKeywordService implements IResearchKeywordService {

	@Autowired
	private IResearchKeywordDAO researchKeywordDAO;
	
	@Override
	public ResearchKeyword findById(Long id) throws SystemException {
		return researchKeywordDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchKeyword anObject) throws SystemException {
		researchKeywordDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchKeywordDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchKeyword> findObjects(Long[] objectPKs)
			throws SystemException {
		return researchKeywordDAO.findObjects(objectPKs);
	}

	@Override
	public PagingWrapper<ResearchKeyword> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchKeywordDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchKeyword> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchKeywordDAO.findAll(filter, order);
	}
}