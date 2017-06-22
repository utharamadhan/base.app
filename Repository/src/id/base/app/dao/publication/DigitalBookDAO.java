package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.DigitalBook;

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
public class DigitalBookDAO extends AbstractHibernateDAO<DigitalBook, Long> implements IDigitalBookDAO {

	@Override
	public DigitalBook findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(DigitalBook anObject) throws SystemException {
		if (anObject.getPkDigitalBook()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<DigitalBook> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			DigitalBook object = DigitalBook.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<DigitalBook> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<DigitalBook> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<DigitalBook> findLatestEbook(int number) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(DigitalBook.STATUS, ILookupConstant.Status.PUBLISH));
		crit.addOrder(Order.desc(DigitalBook.PK_DIGITAL_BOOK));
		crit.setMaxResults(number);
		crit.setProjection(Projections.projectionList().
				add(Projections.property(DigitalBook.PERMALINK)).
				add(Projections.property(DigitalBook.TITLE)).
				add(Projections.property(DigitalBook.COVER_IMAGE_URL)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				DigitalBook obj = new DigitalBook();
				try {
					BeanUtils.copyProperty(obj, DigitalBook.PERMALINK, tuple[0]);
					BeanUtils.copyProperty(obj, DigitalBook.TITLE, tuple[1]);
					BeanUtils.copyProperty(obj, DigitalBook.COVER_IMAGE_URL, tuple[2]);
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
		Criteria crit = getSession().createCriteria(DigitalBook.class);
		crit.add(Restrictions.like(DigitalBook.PERMALINK, permalink, MatchMode.START));
		if(pk!=null){
			crit.add(Restrictions.ne(DigitalBook.PK_DIGITAL_BOOK, pk));
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
	public DigitalBook findByPermalink(String permalink) throws SystemException {
		Criteria criteria = getSession().createCriteria(DigitalBook.class);
		criteria.add(Restrictions.eq(DigitalBook.PERMALINK, permalink));
		criteria.add(Restrictions.eq(DigitalBook.STATUS, ILookupConstant.Status.PUBLISH));
		return (DigitalBook) criteria.uniqueResult();
	}

}