package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.CompanyMachinery;

import java.util.List;

public interface ICompanyMachineryService extends MaintenanceService<CompanyMachinery>{

	public List<CompanyMachinery> findAllByPkCompany(Long pkCompany)
			throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;

	public int deleteAllByCompany(Long pkCompany);

}
