package id.base.app.dao.testimonial;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class TestimonialDAO extends AbstractHibernateDAO<Testimonial, Long> implements ITestimonialDAO {

	@Override
	public Testimonial findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Testimonial anObject) throws SystemException {
		if (anObject.getPkTestimonial()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Testimonial> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Testimonial object = new Testimonial();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Testimonial> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Testimonial> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<Testimonial> findLatest(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Testimonial.STATUS, ILookupConstant.Status.PUBLISH));
		crit.addOrder(Order.desc(Testimonial.TESTIMONIAL_DATE));
		crit.setMaxResults(number);
		return crit.list();
	}
	
	@Override
	public void updateThumb(Long pkTestimonial, String thumbURL) throws SystemException {
		String updateQuery = "UPDATE TESTIMONIAL SET PHOTO_THUMB_URL = :thumbURL WHERE PK_TESTIMONIAL = :pkTestimonial";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong("pkTestimonial", pkTestimonial);
		sqlQuery.setString("thumbURL", thumbURL);
		sqlQuery.executeUpdate();
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		Criteria crit = getSession().createCriteria(Testimonial.class);
		crit.add(Restrictions.like(Testimonial.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(Testimonial.PK_TESTIMONIAL, pk));
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
}