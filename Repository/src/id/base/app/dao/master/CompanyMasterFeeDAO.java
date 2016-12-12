package id.base.app.dao.master;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchAlias;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.master.CompanyMasterFee;
import id.base.app.valueobject.master.CompanyProduct;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyMasterFeeDAO extends AbstractHibernateDAO<CompanyMasterFee, Long> implements ICompanyMasterFeeDAO {

	@Override
	public CompanyMasterFee findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(CompanyMasterFee anObject) throws SystemException {
		if(anObject.getPkCompanyMasterFee()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<CompanyMasterFee> objectList = new ArrayList<CompanyMasterFee>();
		for(Long objectPK : objectPKs){
			CompanyMasterFee obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<CompanyMasterFee> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyMasterFee> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		SearchAlias[] searchAliases = new SearchAlias[]{
											new SearchAlias(CompanyMasterFee.FEE_TYPE, CompanyMasterFee.FEE_TYPE),
											new SearchAlias(CompanyMasterFee.UOM, CompanyMasterFee.UOM)
										};
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, searchAliases, CompanyMasterFee.MAINTENANCE_LIST_FIELDS, filter, order, null);
	}
	
	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(CompanyProduct.class);
		crit.setProjection(Projections.rowCount());
		crit.createAlias(CompanyMasterFee.COMPANY, CompanyMasterFee.COMPANY);
		crit.add(Restrictions.eq(CompanyMasterFee.COMPANY_ID, pkCompany));
		crit.add(Restrictions.eq(CompanyMasterFee.PREDEFINED, Boolean.FALSE));
		List ct = crit.list();
		if (ct!=null) {
			return (Long) ct.get(0);
		}else{
			return 0L;	
		}
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany) {
		String updateQuery = "DELETE FROM company_master_fee WHERE fk_company = ?";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong(0, pkCompany);
		return sqlQuery.executeUpdate();
	}

}
