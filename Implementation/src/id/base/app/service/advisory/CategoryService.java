package id.base.app.service.advisory;

import id.base.app.SystemConstant;
import id.base.app.dao.advisory.ICategoryDAO;
import id.base.app.dao.faq.IFaqDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Faq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Autowired
	private IFaqDAO faqDAO;
    
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
	
	@Override
	public List<Category> findByType(String type) throws SystemException {
		return categoryDAO.findByType(type);
	}
	
	@Override
	public String getFirstPermalinkData(String type) throws SystemException {
		return categoryDAO.getFirstPermalinkData(type);
	}
	
	@Override
	public Category findSimpleDataByPermalink(String permalink) throws SystemException {
		return categoryDAO.findSimpleDataByPermalink(permalink);
	}
	
	@Override
	public List<Category> findSimpleDataForList(String type) throws SystemException {
		return categoryDAO.findSimpleDataForList(type);
	}
	
	@Override
	public List<Category> findSimpleDataForSelect(String type) throws SystemException {
		return categoryDAO.findSimpleDataForSelect(type);
	}

	@Override
	public void updateThumb(Long pkCategory, String thumbURL) throws SystemException {
		categoryDAO.updateThumb(pkCategory, thumbURL);
	}
	
	@Override
	public List<Category> findCategoryWithFaqList() throws SystemException{
		List<Category> catList = categoryDAO.findSimpleDataForSelect(SystemConstant.CategoryType.FAQ);
		for (Category cat : catList) {
			List<Faq> faqList = faqDAO.findByCategory(cat.getPkCategory());
			cat.setFaqs(faqList);
		}
		return catList;
	}

}
