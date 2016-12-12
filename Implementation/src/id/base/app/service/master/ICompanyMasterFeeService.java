package id.base.app.service.master;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.master.CompanyMasterFee;

import java.util.List;

public interface ICompanyMasterFeeService extends MaintenanceService<CompanyMasterFee>{

	public List<CompanyMasterFee> findAllByFeeType(Long pkCompany, String feeType)
			throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;

	public int deleteAllByCompany(Long pkCompany);

}
