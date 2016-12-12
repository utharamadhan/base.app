package id.padiku.app.dao.master;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.CompanyMachinery;

public interface ICompanyMachineryDAO extends IBaseDAO<CompanyMachinery>{

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;
	
	public int deleteAllByCompany(Long pkCompany);

}
