package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.LinkUrl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class LinkUrlDAO extends AbstractHibernateDAO<LinkUrl, Long> implements ILinkUrlDAO {

	@Override
	public LinkUrl findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(LinkUrl anObject) throws SystemException {
		if (anObject.getPkLinkUrl()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<LinkUrl> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			LinkUrl object = new LinkUrl();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<LinkUrl> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<LinkUrl> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(LinkUrl.class);
		crit.add(Restrictions.like(LinkUrl.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(LinkUrl.PK_LINK_URL, pk));
		}
		crit.setProjection(Projections.projectionList().add(Projections.property("permalink")));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				String r = null;
				try{
					r = tuple[0].toString();
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
				return r;
			}
			
			@Override
			public List<?> transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public List<LinkUrl> findByPermalinkParent(String permalink) throws SystemException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT LU.TITLE, LU.URL FROM LINK_URL LU "
				+ "INNER JOIN LINK_URL LUP ON LUP.PK_LINK_URL = LU.FK_LINK_URL_PARENT "
				+ "WHERE LU.STATUS = 2 AND LUP.PERMALINK = :permalink ORDER BY LU.ORDER_NO ASC, LU.TITLE ASC ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("permalink", permalink);
		query.setResultTransformer(new ResultTransformer() {
			
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				LinkUrl linkUrl = new LinkUrl();
				linkUrl.setTitle(tuple[0] == null ? null : String.valueOf(tuple[0]));
				linkUrl.setUrl(tuple[1] == null ? null : String.valueOf(tuple[1]));
				return linkUrl;
			}
			@SuppressWarnings("rawtypes")
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return query.list();
	}
	
	@Override
	public String getTitleByPermalink(String permalink) throws SystemException {
		Query query = getSession().createSQLQuery("SELECT TITLE FROM LINK_URL WHERE PERMALINK = :permalink");
		query.setString("permalink", permalink);
		return ((String) query.uniqueResult()).toString();
	}
	
	@Override
	public Long getPkByPermalink(String permalink) throws SystemException {
		Query query = getSession().createSQLQuery("SELECT PK_LINK_URL FROM LINK_URL WHERE PERMALINK = :permalink");
		query.setString("permalink", permalink);
		return Long.valueOf(query.uniqueResult().toString());
	}
}