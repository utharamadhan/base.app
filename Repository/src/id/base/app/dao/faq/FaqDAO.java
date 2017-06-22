package id.base.app.dao.faq;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Faq;
import id.base.app.valueobject.Lookup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class FaqDAO extends AbstractHibernateDAO<Faq,Long> implements IFaqDAO {

	public Faq findById(Long lookupId) throws SystemException {
		return super.findByPK(lookupId);
	}

	public void saveOrUpdate(Faq lookup) throws SystemException {
		if(lookup.getPkFaq()==null)
			super.create(lookup);
		else
		    super.update(lookup);
	}
	
	@Override
	public List<Faq> findAll(List<SearchFilter> filters,
			List<SearchOrder> orders) throws SystemException {
		return super.findAll(filters, orders, null);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Faq> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			Faq object = new Faq();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<Faq> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Faq> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<Faq> findFaqListForView() throws SystemException {
		Criteria crit = getSession().createCriteria(Faq.class);
		crit.createAlias("faqCategoryLookup", "faqCategoryLookup");
		crit.add(Restrictions.eq(Faq.STATUS, ILookupConstant.Status.PUBLISH));
		crit.addOrder(Order.asc("faqCategoryLookup.code"));
		crit.addOrder(Order.asc(Faq.QUESTION));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property(Faq.QUESTION));
		projectionList.add(Projections.property(Faq.ANSWER));
		projectionList.add(Projections.property("faqCategoryLookup.code"));
		crit.setProjection(projectionList);
		crit.setResultTransformer(new ResultTransformer() {
		
		@Override
		public Object transformTuple(Object[] tuple, String[] aliases) {
			Faq faq = new Faq();
			try{					
				faq.setQuestion(tuple[0].toString());
				faq.setAnswer(tuple[1].toString());
				Lookup lookup = new Lookup();
				lookup.setCode(tuple[3].toString());
				faq.setFaqCategoryLookup(lookup);
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
			return faq;
		}
		
		@Override
		public List transformList(List collection) {
			return collection;
		}
	});
	
	return crit.list();
	}	
}