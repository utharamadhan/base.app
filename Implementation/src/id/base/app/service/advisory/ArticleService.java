package id.base.app.service.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.advisory.IArticleDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.advisory.Article;

@Service
@Transactional
public class ArticleService implements IArticleService {

	@Autowired
	private IArticleDAO articleDAO;
    
	public PagingWrapper<Tutor> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Article findById(Long id) throws SystemException {
		return articleDAO.findById(id);
	}

	public void saveOrUpdate(Article anObject) throws SystemException {
		articleDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		articleDAO.delete(objectPKs);
	}

	public PagingWrapper<Article> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return articleDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Article> findObjects(Long[] objectPKs) throws SystemException {
		List<Article> objects = new ArrayList<>();
		Article object = null;
		for(Long l:objectPKs){
			object = articleDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Article> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return articleDAO.findAll(filter, order);
	}

}
