package id.padiku.app.dao.transaction;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.production.TransProd;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TransProdDAO extends AbstractHibernateDAO<TransProd, Long> implements ITransProdDAO {

	@Override
	public TransProd findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	public String generateTransProdNumber(Long pkCompany) throws SystemException {
		String transProdNumber = "";
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.addOrder(Order.desc("prodNo"));
			crit.setProjection(Projections.projectionList().add(Projections.property("prodNo")));
			crit.setMaxResults(1);
		Object returnObj = crit.uniqueResult();
		if(returnObj != null && StringFunction.isNotEmpty(returnObj.toString())){
			Long noLong = Long.valueOf(returnObj.toString());
			transProdNumber = String.format("%05d", ++noLong);
		}else{
			transProdNumber = String.format("%05d", 1);
		}
		return transProdNumber;
	}
	
	@Override
	public void saveOrUpdate(TransProd anObject) throws SystemException {
		if(anObject.getPkTransProd()!=null){
			super.update(anObject);
		}else{
			anObject.setProdNo(generateTransProdNumber(anObject.getCompany().getPkCompany()));
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			TransProd obj = findById(objectPK);
				obj.setStatus(SystemConstant.StatusProd.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<TransProd> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransProd> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public Long countTransProd(Long pkCompany){
		Criteria crit = getSession().createCriteria(TransProd.class);
		crit.setProjection(Projections.rowCount());
		crit.createAlias(TransProd.COMPANY, TransProd.COMPANY);
		crit.add(Restrictions.eq(TransProd.COMPANY_ID, pkCompany));
		crit.add(Restrictions.eq(TransProd.STATUS, SystemConstant.StatusProd.VALID));
		List ct = crit.list();
		if (ct!=null) {
			return (Long) ct.get(0);
		}else{
			return 0L;	
		}
	}

}
