package id.padiku.app.dao.transaction;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.sales.TransOut;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TransOutDAO extends AbstractHibernateDAO<TransOut, Long> implements ITransOutDAO {

	@Override
	public TransOut findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(TransOut anObject) throws SystemException {
		if(anObject.getPkTransOut()!=null){
			super.update(anObject);
		}else{
			anObject.setOutNo(generateTransOutNumber(anObject.getCompany().getPkCompany()));
			super.create(anObject);
		}
	}
	
	public String generateTransOutNumber(Long pkCompany) throws SystemException {
		String transInNumber = "";
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.addOrder(Order.desc("outNo"));
			crit.setProjection(Projections.projectionList().add(Projections.property("outNo")));
			crit.setMaxResults(1);
		Object returnObj = crit.uniqueResult();
		if(returnObj != null && StringFunction.isNotEmpty(returnObj.toString())){
			Long poNumberLong = Long.valueOf(returnObj.toString());
			transInNumber = String.format("%05d", ++poNumberLong);
		}else{
			transInNumber = String.format("%05d", 1);
		}
		return transInNumber;
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			TransOut obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<TransOut> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransOut> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
