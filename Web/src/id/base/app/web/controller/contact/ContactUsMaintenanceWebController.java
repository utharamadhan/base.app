package id.base.app.web.controller.contact;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.ContactSetting;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/contactUs/maintenance")
public class ContactUsMaintenanceWebController extends BaseController<ContactSetting> {
	
	private final String PATH_DETAIL = "/contact/contactUsMaintenanceDetail";

	@Override
	protected RestCaller<ContactSetting> getRestCaller() {
		return new RestCaller<ContactSetting>(RestConstant.REST_SERVICE, RestServiceConstant.CONTACT_SETTING_SERVICE);
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
	
	@RequestMapping(method=RequestMethod.POST, value="saveContactSetting")
	@ResponseBody
	public Map<String, Object> saveContactSetting(final ContactSetting anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<ContactSetting>(RestConstant.REST_SERVICE, RestServiceConstant.CONTACT_SETTING_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showMain")
	public String showMain(ModelMap model, HttpServletRequest request){
		ContactSetting detail = ContactSetting.getInstance();
		List<ContactSetting> list = getRestCaller().findAll(filters, orders);
		if(!list.isEmpty()){
			detail = list.get(0);
		}
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}

}
