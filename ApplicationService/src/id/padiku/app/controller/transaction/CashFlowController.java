package id.padiku.app.controller.transaction;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.transaction.ICashFlowService;
import id.padiku.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_CASH_FLOW)
public class CashFlowController extends SuperController<ViewCashFlow> {

	@Autowired
	private ICashFlowService cashFlowService;
	
	@Override
	public MaintenanceService<ViewCashFlow> getMaintenanceService() {
		return null;
	}

	@Override
	public ViewCashFlow validate(ViewCashFlow anObject) throws SystemException {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getCashFlowFee/{pkCompany}")
	@ResponseBody
	public List<ViewCashFlow> getCashFlowFee(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return cashFlowService.getCashFlowFee(pkCompany);
	}

}
