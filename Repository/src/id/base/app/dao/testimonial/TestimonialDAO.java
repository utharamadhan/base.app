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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		crit.add(Restrictions.eq(Testimonial.STATUS, ILookupConstant.ArticleStatus.PUBLISH));
		crit.addOrder(Order.desc(Testimonial.TESTIMONIAL_DATE));
		crit.setMaxResults(number);
		return crit.list();
	}
}