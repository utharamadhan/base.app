package id.base.app.dao.aboutUs;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.ProgramPost;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramPostDAO extends AbstractHibernateDAO<ProgramPost, Long> implements IProgramPostDAO {

	@Override
	public ProgramPost findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ProgramPost anObject) throws SystemException {
		if (anObject.getPkProgramPost()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ProgramPost> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ProgramPost object = new ProgramPost();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ProgramPost> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ProgramPost> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<ProgramPost> findLatest(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(ProgramPost.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		crit.addOrder(Order.desc(ProgramPost.PK_PROGRAM_POST));
		crit.setMaxResults(number);
		crit.setProjection(Projections.projectionList().
				add(Projections.property(ProgramPost.PERMALINK)).
				add(Projections.property(ProgramPost.TITLE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				ProgramPost obj = new ProgramPost();
				try {
					BeanUtils.copyProperty(obj, ProgramPost.PERMALINK, tuple[0]);
					BeanUtils.copyProperty(obj, ProgramPost.TITLE, tuple[1]);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return obj;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(ProgramPost.class);
		crit.add(Restrictions.like(ProgramPost.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(ProgramPost.PK_PROGRAM_POST, pk));
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
	public ProgramPost findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(ProgramPost.class);
		criteria.add(Restrictions.eq(ProgramPost.PERMALINK, permalink));
		criteria.add(Restrictions.eq(ProgramPost.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		return (ProgramPost) criteria.uniqueResult();
	}
}