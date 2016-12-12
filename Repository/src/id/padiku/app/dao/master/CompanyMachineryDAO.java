package id.padiku.app.dao.master;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.CompanyMachinery;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyMachineryDAO extends AbstractHibernateDAO<CompanyMachinery, Long> implements ICompanyMachineryDAO {

	@Override
	public CompanyMachinery findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(CompanyMachinery anObject) throws SystemException {
		if(anObject.getPkCompanyMachinery()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<CompanyMachinery> objectList = new ArrayList<CompanyMachinery>();
		for(Long objectPK : objectPKs){
			CompanyMachinery obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<CompanyMachinery> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyMachinery> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(CompanyMachinery.class);
		crit.setProjection(Projections.rowCount());
		crit.createAlias(CompanyMachinery.COMPANY, CompanyMachinery.COMPANY);
		crit.add(Restrictions.eq(CompanyMachinery.COMPANY_ID, pkCompany));
		crit.add(Restrictions.eq(CompanyMachinery.PREDEFINED, Boolean.FALSE));
		List ct = crit.list();
		if (ct!=null) {
			return (Long) ct.get(0);
		}else{
			return 0L;	
		}
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany) {
		String updateQuery = "DELETE FROM company_machinery WHERE fk_company = ?";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong(0, pkCompany);
		return sqlQuery.executeUpdate();
	}

}
