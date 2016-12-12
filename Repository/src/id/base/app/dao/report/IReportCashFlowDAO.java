package id.base.app.dao.report;

import id.base.app.exception.SystemException;
import id.base.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

public interface IReportCashFlowDAO {

	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException;

}
