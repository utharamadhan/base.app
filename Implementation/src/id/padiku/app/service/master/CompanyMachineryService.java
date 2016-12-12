package id.padiku.app.service.master;

import id.padiku.app.SystemConstant;
import id.padiku.app.dao.master.ICompanyMachineryDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.CompanyMachinery;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class CompanyMachineryService extends QueryTransformer<CompanyMachinery> implements ICompanyMachineryService{

	@Autowired
	private ICompanyMachineryDAO companyMachineryDAO;
	
	@Override
	public CompanyMachinery findById(Long id) throws SystemException {
		return companyMachineryDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(CompanyMachinery anObject) throws SystemException {
		anObject.setPredefined(Boolean.FALSE);
		companyMachineryDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		companyMachineryDAO.delete(objectPKs);
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
		return companyMachineryDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<CompanyMachinery> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public List<CompanyMachinery> findAllByPkCompany(Long pkCompany) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(CompanyMachinery.COMPANY_ID, pkCompany));
		exp.add(Expression.eq(CompanyMachinery.STATUS, SystemConstant.ValidFlag.VALID));
		DetachedCriteria detachedCriteria = criteriaByProperty(CompanyMachinery.MAINTENANCE_LIST_FOR_TRANS_PROD, exp);
		return companyMachineryDAO.loadAll(detachedCriteria);
	}

	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException{
		return companyMachineryDAO.countNotPredefinedAllByCompany(pkCompany);
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany){
		return companyMachineryDAO.deleteAllByCompany(pkCompany);
	}
	
}
