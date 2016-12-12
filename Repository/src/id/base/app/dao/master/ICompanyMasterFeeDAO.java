package id.base.app.dao.master;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.CompanyMasterFee;

public interface ICompanyMasterFeeDAO extends IBaseDAO<CompanyMasterFee>{

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;
	
	public int deleteAllByCompany(Long pkCompany);

}
