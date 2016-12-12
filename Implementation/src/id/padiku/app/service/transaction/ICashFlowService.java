package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

public interface ICashFlowService extends MaintenanceService<ViewCashFlow>{

	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException;

}
