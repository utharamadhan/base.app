package id.base.app.dao.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.Article;

@Repository
public class ArticleDAO extends AbstractHibernateDAO<Article, Long> implements IArticleDAO {

	@Override
	public Article findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Article anObject) throws SystemException {
		if (anObject.getPkArticle()==null) {
			anObject.setArticleTime(DateTimeFunction.getCurrentDate());
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Article> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Article object = new Article();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Article> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Article> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
