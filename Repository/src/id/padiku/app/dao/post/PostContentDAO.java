package id.padiku.app.dao.post;

import java.util.List;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.IBaseDAO;
import id.padiku.app.dao.transaction.IPurchaseOrderDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.post.PostContent;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

import org.springframework.stereotype.Repository;

@Repository
public class PostContentDAO extends AbstractHibernateDAO<PostContent, Long> implements IBaseDAO<PostContent>, IPostContentDAO {

	@Override
	public PostContent findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(PostContent anObject) throws SystemException {
		if(anObject.getPkPostContent()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<PostContent> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<PostContent> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}
}