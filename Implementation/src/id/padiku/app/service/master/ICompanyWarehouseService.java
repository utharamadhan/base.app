package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.CompanyWarehouse;

import java.util.List;

public interface ICompanyWarehouseService extends MaintenanceService<CompanyWarehouse>{
	
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException;

}