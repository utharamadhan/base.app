package id.base.app.service.master;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.master.CompanyWarehouse;

import java.util.List;

public interface ICompanyWarehouseService extends MaintenanceService<CompanyWarehouse>{
	
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException;

}