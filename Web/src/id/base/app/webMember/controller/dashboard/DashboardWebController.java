package id.base.app.webMember.controller.dashboard;

import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.notification.Notification;

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
		model.addAttribute("notifications", getLastFiveNotifications());
		model.addAttribute("unreadNotifications", countUnreadNotifications());
		return "/dashboard/dashboardDetail";
	}
	
	public List<Notification> getLastFiveNotifications() {
		return new SpecificRestCaller<Notification>(RestConstant.REST_SERVICE, RestConstant.RM_NOTIFICATION, Notification.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getLastFiveNotifications";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
	private Integer countUnreadNotifications() {
		return new SpecificRestCaller<Integer>(RestConstant.REST_SERVICE, RestConstant.RM_NOTIFICATION, Integer.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/countUnreadNotifications";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<String, Object>();
			}
		});
	}
	
}
