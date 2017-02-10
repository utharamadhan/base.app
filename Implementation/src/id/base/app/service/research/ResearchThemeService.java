package id.base.app.service.research;

import id.base.app.dao.research.IResearchThemeDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTheme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchThemeService implements IResearchThemeService {

	@Autowired
	private IResearchThemeDAO researchThemeDAO;
    
	public PagingWrapper<ResearchTheme> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ResearchTheme findById(Long id) throws SystemException {
		return researchThemeDAO.findById(id);
	}

	public void saveOrUpdate(ResearchTheme anObject) throws SystemException {
		researchThemeDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		researchThemeDAO.delete(objectPKs);
	}

	public PagingWrapper<ResearchTheme> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchThemeDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ResearchTheme> findObjects(Long[] objectPKs) throws SystemException {
		List<ResearchTheme> objects = new ArrayList<>();
		ResearchTheme object = null;
		for(Long l:objectPKs){
			object = researchThemeDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ResearchTheme> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchThemeDAO.findAll(filter, order);
	}

	@Override
	public List<ResearchTheme> findAllResearchThemeTitle() throws SystemException {
		return researchThemeDAO.findAllResearchThemeTitle();
	}

}
