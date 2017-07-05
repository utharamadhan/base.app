package id.base.app.service.research;

import id.base.app.dao.research.IResearchDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.Research;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchService implements IResearchService {

	@Autowired
	private IResearchDAO researchDAO;
    
	public PagingWrapper<Research> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Research findById(Long id) throws SystemException {
		return researchDAO.findById(id);
	}

	public void saveOrUpdate(Research anObject) throws SystemException {
		researchDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		researchDAO.delete(objectPKs);
	}

	public PagingWrapper<Research> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Research> findObjects(Long[] objectPKs) throws SystemException {
		List<Research> objects = new ArrayList<>();
		Research object = null;
		for(Long l:objectPKs){
			object = researchDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Research> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchDAO.findAll(filter, order);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return researchDAO.getSamePermalink(pk, permalink);
	}
	
	@Override
	public Research findByPermalink(String permalink) throws SystemException {
		return researchDAO.findByPermalink(permalink);
	}

}
