package id.padiku.app.dao.master;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.CompanyWarehouse;

import java.util.List;

public interface ICompanyWarehouseDAO extends IBaseDAO<CompanyWarehouse>{
	
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException;

}
