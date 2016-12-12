package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.Company;

import java.util.List;

public interface ICompanyService extends MaintenanceService<Company>{
	
	public Company getCompanyByUser(Long pkAppUser) throws SystemException;
	
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException;

}
