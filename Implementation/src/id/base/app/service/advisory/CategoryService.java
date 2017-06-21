package id.base.app.service.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.advisory.ICategoryDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;

@Service
@Transactional
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDAO categoryDAO;
    
	public PagingWrapper<Category> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Category findById(Long id) throws SystemException {
		return categoryDAO.findById(id);
	}

	public void saveOrUpdate(Category anObject) throws SystemException {
		categoryDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		categoryDAO.delete(objectPKs);
	}

	public PagingWrapper<Category> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return categoryDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Category> findObjects(Long[] objectPKs) throws SystemException {
		List<Category> objects = new ArrayList<>();
		Category object = null;
		for(Long l:objectPKs){
			object = categoryDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Category> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return categoryDAO.findAll(filter, order);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return categoryDAO.getSamePermalink(pk, permalink);
	}

}
