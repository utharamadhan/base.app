package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.Research;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ResearchDAO extends AbstractHibernateDAO<Research, Long> implements IResearchDAO {

	@Override
	public Research findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Research anObject) throws SystemException {
		if (anObject.getPkResearch()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Research> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Research object = Research.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Research> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Research> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(Research.class);
		crit.add(Restrictions.like(Research.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(Research.PK_RESEARCH, pk));
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
	public Research findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(Research.class);
		criteria.add(Restrictions.eq(Research.PERMALINK, permalink));
		criteria.add(Restrictions.eq(Research.STATUS, ILookupConstant.Status.PUBLISH));
		return (Research) criteria.uniqueResult();
	}

}
