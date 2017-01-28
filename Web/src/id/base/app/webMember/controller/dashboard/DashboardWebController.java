package id.base.app.webMember.controller.dashboard;

import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.contact.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@Controller
@RequestMapping(RestConstant.RM_DASHBOARD)
public class DashboardWebController{

	protected static Logger LOGGER = LoggerFactory.getLogger(DashboardWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request){
		model.addAttribute("contacts", getLatestContactUs());
		model.addAttribute("unreadContactsMessage", countUnreadMessage());
		return "/dashboard/dashboardDetail";
	}
	
	private List<Contact> getLatestContactUs() {
		return new SpecificRestCaller<Contact>(RestConstant.REST_SERVICE, RestConstant.RM_CONTACT, Contact.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getLatestContactUs";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
	private Integer countUnreadMessage() {
		return new SpecificRestCaller<Integer>(RestConstant.REST_SERVICE, RestConstant.RM_CONTACT, Integer.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/countUnreadMessage";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
}
