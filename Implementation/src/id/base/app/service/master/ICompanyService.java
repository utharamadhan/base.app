package id.base.app.service.master;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.master.Company;

import java.util.List;

public interface ICompanyService extends MaintenanceService<Company>{
	
	public Company getCompanyByUser(Long pkAppUser) throws SystemException;
	
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException;

}
