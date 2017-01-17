package id.base.app.webMember.researchManagement;

import id.base.app.rest.RestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.Research;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope(value="request")
@Controller
@RequestMapping("/researchManagement")
public class ResearchManagementWebController extends BaseController<Research> {

	@Override
	protected RestCaller<Research> getRestCaller() {
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
