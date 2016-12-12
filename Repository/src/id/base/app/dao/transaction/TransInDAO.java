package id.base.app.dao.transaction;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.procurement.TransIn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TransInDAO extends AbstractHibernateDAO<TransIn, Long> implements ITransInDAO {

	@Override
	public TransIn findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(TransIn anObject) throws SystemException {
		if(anObject.getPkTransIn()!=null){
			super.update(anObject);
		}else{
			anObject.setInNo(generateTransInNumber(anObject.getCompany().getPkCompany()));
			super.create(anObject);
		}
	}
	
	public String generateTransInNumber(Long pkCompany) throws SystemException {
		String transInNumber = "";
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.addOrder(Order.desc("inNo"));
			crit.setProjection(Projections.projectionList().add(Projections.property("inNo")));
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
		List<TransIn> objectList = new ArrayList<TransIn>();
		for(Long objectPK : objectPKs){
			TransIn obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<TransIn> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransIn> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public Long countTransIn(Long pkCompany, String transInSourceType) throws SystemException {
		Criteria crit = getSession().createCriteria(TransIn.class);
		crit.setProjection(Projections.rowCount());
		crit.createAlias(TransIn.COMPANY, TransIn.COMPANY);
		crit.createAlias(TransIn.TRANS_IN_ITEMS, TransIn.TRANS_IN_ITEMS);
		crit.add(Restrictions.eq(TransIn.COMPANY_ID, pkCompany));
		crit.add(Restrictions.eq(TransIn.SOURCE_TYPE, transInSourceType));
		crit.add(Restrictions.eq(TransIn.STATUS, SystemConstant.ValidFlag.VALID));
		crit.add(Restrictions.eq(TransIn.TRANS_IN_ITEMS_STATUS, SystemConstant.StatusTransInItem.VALID));
		List ct = crit.list();
		if (ct!=null) {
			return (Long) ct.get(0);
		}else{
			return 0L;	
		}
	}
}