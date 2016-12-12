package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

public interface ICashFlowService extends MaintenanceService<ViewCashFlow>{

	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException;

}
