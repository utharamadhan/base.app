package id.padiku.app.dao.master;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.Company;

import java.util.List;

public interface ICompanyDAO extends IBaseDAO<Company>{

	public Company getCompanyByUser(Long pkAppUser) throws SystemException;
	
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException;
	
}
