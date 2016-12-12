package id.padiku.app.dao.master;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.CompanyProduct;

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
