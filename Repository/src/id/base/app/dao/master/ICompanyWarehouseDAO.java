package id.base.app.dao.master;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.CompanyWarehouse;

import java.util.List;

public interface ICompanyWarehouseDAO extends IBaseDAO<CompanyWarehouse>{
	
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException;

}
