package id.base.app.webMember.controller.notification;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.notification.Notification;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

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

@Scope(value="request")
@Controller
@RequestMapping("/notification")
public class NotificationWebController extends BaseController<Notification> {

	private final String PATH_LIST = "/notification/notificationList";
	private final String NOTIFICATION_HEADER_SECTION = "/notification/_notificationHeader";
	
	@Override
	protected RestCaller<Notification> getRestCaller() {
		return new RestCaller<Notification>(RestConstant.REST_SERVICE, RestServiceConstant.NOTIFICATION_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		/*setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(Notification.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}*/
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(Notification.PK_NOTIFICATION, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showLastFiveNotifications")
	public String showLastFiveNotifications(ModelMap model, HttpServletRequest request){
		model.addAttribute("notifications", getLastFiveNotifications());
		return NOTIFICATION_HEADER_SECTION;
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
	
	public void setDefaultData(ModelMap model) {
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}

}
