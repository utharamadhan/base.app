package id.base.app.service.master;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.master.CompanyMachinery;

import java.util.List;

public interface ICompanyMachineryService extends MaintenanceService<CompanyMachinery>{

	public List<CompanyMachinery> findAllByPkCompany(Long pkCompany)
			throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;

	public int deleteAllByCompany(Long pkCompany);

}
