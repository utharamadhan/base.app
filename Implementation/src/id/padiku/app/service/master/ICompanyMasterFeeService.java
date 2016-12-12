package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.CompanyMasterFee;

import java.util.List;

public interface ICompanyMasterFeeService extends MaintenanceService<CompanyMasterFee>{

	public List<CompanyMasterFee> findAllByFeeType(Long pkCompany, String feeType)
			throws SystemException;

	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException;

	public int deleteAllByCompany(Long pkCompany);

}
