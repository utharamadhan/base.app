package id.padiku.app.webMember.controller.report;

import id.padiku.app.rest.RestCaller;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.sales.TransOut;
import id.padiku.app.webMember.DataTableCriterias;
import id.padiku.app.webMember.controller.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope(value="request")
@Controller
@RequestMapping("/reports/penjualan")
public class ReportTransOutWebController extends BaseController<TransOut> {

	@Override
	protected RestCaller<TransOut> getRestCaller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getListPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
