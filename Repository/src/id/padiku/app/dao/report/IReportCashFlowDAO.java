package id.padiku.app.dao.report;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

public interface IReportCashFlowDAO {

	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException;

}
