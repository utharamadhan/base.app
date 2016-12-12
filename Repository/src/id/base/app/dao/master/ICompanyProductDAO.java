package id.base.app.dao.master;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.CompanyProduct;

import java.util.List;

public interface ICompanyProductDAO extends IBaseDAO<CompanyProduct>{

	public List<CompanyProduct> findAll(Long pkCompany) throws SystemException;

	public List<CompanyProduct> findAll(Long pkCompany, String keyword) throws SystemException;
	
	public List<CompanyProduct> findAllProductByUsageType(Long pkCompany, String usageItemType) throws SystemException;

	public Boolean isMachineryUsedThisProduct(Long pkCompanyProduct) throws SystemException;

	public List<CompanyProduct> findAllExistInStock(Long pkCompany) throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;
	
	public int deleteAllByCompany(Long pkCompany);
	
}
