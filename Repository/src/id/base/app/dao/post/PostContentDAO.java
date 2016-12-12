package id.base.app.dao.post;

import java.util.List;

import id.base.app.AbstractHibernateDAO;
import id.base.app.IBaseDAO;
import id.base.app.dao.transaction.IPurchaseOrderDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.post.PostContent;
import id.base.app.valueobject.procurement.PurchaseOrder;

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