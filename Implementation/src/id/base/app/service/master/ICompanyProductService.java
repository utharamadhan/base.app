package id.base.app.service.master;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;

import java.util.List;

public interface ICompanyProductService extends MaintenanceService<CompanyProduct>{
	
	public List<CompanyProduct> findAll(Long pkCompany) throws SystemException;
	
	public List<CompanyProduct> findAll(Long pkCompany, String keyword) throws SystemException;
	
	public List<CompanyProduct> findAllProductByUsageType(Long pkCompany, String usageItemType) throws SystemException;

	public Boolean isMachineryUsedThisProduct(Long pkCompanyProduct) throws SystemException;

	public Lookup findItemType(Long pkCompanyProduct) throws SystemException;

	public List<CompanyProduct> findAllExistInStock(Long pkCompany) throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;

	public int deleteAllByCompany(Long pkCompany);

}
