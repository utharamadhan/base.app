package id.base.app.service.news;

import id.base.app.dao.news.INewsDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.news.News;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsService implements INewsService {

	@Autowired
	private INewsDAO newsDAO;
    
	public PagingWrapper<News> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public News findById(Long id) throws SystemException {
		return newsDAO.findById(id);
	}

	public void saveOrUpdate(News anObject) throws SystemException {
		newsDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		newsDAO.delete(objectPKs);
	}

	public PagingWrapper<News> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return newsDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<News> findObjects(Long[] objectPKs) throws SystemException {
		List<News> objects = new ArrayList<>();
		News object = null;
		for(Long l:objectPKs){
			object = newsDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<News> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return newsDAO.findAll(filter, order);
	}

}