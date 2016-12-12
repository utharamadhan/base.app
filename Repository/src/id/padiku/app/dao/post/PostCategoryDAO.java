package id.padiku.app.dao.post;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.post.PostCategory;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PostCategoryDAO extends AbstractHibernateDAO<PostCategory, Long> implements IBaseDAO<PostCategory>, IPostCategoryDAO {

	@Override
	public PostCategory findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(PostCategory anObject) throws SystemException {
		if(anObject.getPkPostCategory()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<PostCategory> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<PostCategory> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}
}