package id.base.app.dao.master;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.Company;

import java.util.List;

public interface ICompanyDAO extends IBaseDAO<Company>{

	public Company getCompanyByUser(Long pkAppUser) throws SystemException;
	
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException;
	
}
