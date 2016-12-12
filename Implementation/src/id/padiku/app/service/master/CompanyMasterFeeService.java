package id.padiku.app.service.master;

import id.padiku.app.SystemConstant;
import id.padiku.app.dao.master.ICompanyMasterFeeDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.CompanyMasterFee;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class CompanyMasterFeeService extends QueryTransformer<CompanyMasterFee> implements ICompanyMasterFeeService{

	@Autowired
	private ICompanyMasterFeeDAO companyMasterFeeDAO;
	
	@Override
	public CompanyMasterFee findById(Long id) throws SystemException {
		return companyMasterFeeDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(CompanyMasterFee anObject) throws SystemException {
		anObject.setPredefined(Boolean.FALSE);
		companyMasterFeeDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		companyMasterFeeDAO.delete(objectPKs);
	}

	@Override
	public List<CompanyMasterFee> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyMasterFee> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return companyMasterFeeDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<CompanyMasterFee> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public List<CompanyMasterFee> findAllByFeeType(Long pkCompany, String feeType) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(CompanyMasterFee.COMPANY_ID, pkCompany));
		exp.add(Expression.eq(CompanyMasterFee.FEE_TYPE_CODE, feeType));
		exp.add(Expression.eq(CompanyMasterFee.STATUS, SystemConstant.ValidFlag.VALID));
		DetachedCriteria detachedCriteria = criteriaByProperty(CompanyMasterFee.MAINTENANCE_LIST_FIELDS, exp);
		return companyMasterFeeDAO.loadAll(detachedCriteria);
	}
	
	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException{
		return companyMasterFeeDAO.countNotPredefinedAllByCompany(pkCompany);
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany){
		return companyMasterFeeDAO.deleteAllByCompany(pkCompany);
	}
}
