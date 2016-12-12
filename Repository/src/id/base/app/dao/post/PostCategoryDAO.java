package id.base.app.dao.post;

import id.base.app.AbstractHibernateDAO;
import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.post.PostCategory;

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