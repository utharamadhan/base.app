package id.base.app.web.controller.frontEndDisplay;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.HomeSettingHelper;
import id.base.app.valueobject.frontend.Setting;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/fed/homeSetting")
public class HomeSettingWebController extends BaseController<Setting>{

	private final String PATH_DETAIL = "/fed/homeSettingDetail";
	
	@Override
	protected RestCaller<Setting> getRestCaller() {
		return new RestCaller<Setting>(RestConstant.REST_SERVICE, RestServiceConstant.SETTING_SERVICE);
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		return null;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		HomeSettingHelper setting = getHomeSettingList();
		model.addAttribute("setting", setting);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveHomeSetting")
	@ResponseBody
	public Map<String, Object> saveHomeSetting(final Setting anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Setting>(RestConstant.REST_SERVICE, RestServiceConstant.SETTING_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@Override
	protected String getListPath() {
		return PATH_DETAIL;
	}
	
	private HomeSettingHelper getHomeSettingList(){
		SpecificRestCaller<HomeSettingHelper> rc = new SpecificRestCaller<HomeSettingHelper>(RestConstant.REST_SERVICE, RestServiceConstant.HOME_SETTING_SERVICE);
		HomeSettingHelper obj = rc.executeGet(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findAllSettingHome";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				return map;
			}
		});
		return obj;
	}
}
