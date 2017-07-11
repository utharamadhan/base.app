package id.base.app.service.frontEndDisplay;

import id.base.app.dao.frontEndDisplay.IPagesDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PagesService implements IPagesService {

	@Autowired
	private IPagesDAO pagesDAO;
    
	public PagingWrapper<Pages> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Pages findById(Long id) throws SystemException {
		return pagesDAO.findById(id);
	}

	public void saveOrUpdate(Pages anObject) throws SystemException {
		pagesDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		pagesDAO.delete(objectPKs);
	}

	public PagingWrapper<Pages> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return pagesDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Pages> findObjects(Long[] objectPKs) throws SystemException {
		List<Pages> objects = new ArrayList<>();
		Pages object = null;
		for(Long l:objectPKs){
			object = pagesDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Pages> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return pagesDAO.findAll(filter, order);
	}
	
	@Override
	public Pages findByPermalink(String permalink) throws SystemException {
		return pagesDAO.findByPermalink(permalink);
	}
	
	@Override
	public List<Pages> findSimpleData(String type) throws SystemException {
		return pagesDAO.findSimpleData(type);
	}
	
	@Override
	public List<Pages> getLatestPages(List<String> types) throws SystemException {
		return pagesDAO.getLatestPages(types);
	}
	
	
	
}
