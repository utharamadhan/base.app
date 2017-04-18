package id.base.app.web.controller.contact;

import id.base.app.rest.RestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@Controller
@RequestMapping("/contactUs/maintenance")
public class ContactUsMaintenanceWebController extends BaseController<Contact> {
	
	private final String PATH_DETAIL = "/contact/contactUsMaintenanceDetail";

	@Override
	protected RestCaller<Contact> getRestCaller() {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		return null;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
	}

	@Override
	protected String getListPath() {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showDetail")
	public String showDetail(ModelMap model, HttpServletRequest request){
		return PATH_DETAIL;
	}

}
