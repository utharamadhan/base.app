package id.base.app.web.controller.frontEndDisplay;

import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.frontend.Setting;

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

@Scope(value="request")
@Controller
@RequestMapping("/fed/menuSetting")
public class MenuSettingWebController {

	private final String PATH_DETAIL = "/fed/menuSettingDetail";
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		List<Setting> menuTop = getMenuSettingList(true);
		List<Setting> menuBottom = getMenuSettingList(false);
		model.addAttribute("menuTop", menuTop);
		model.addAttribute("menuBottom", menuBottom);
		return PATH_DETAIL;
	}
	
	private List<Setting> getMenuSettingList(final Boolean isTop){
		SpecificRestCaller<Setting> rc = new SpecificRestCaller<Setting>(RestConstant.REST_SERVICE, RestServiceConstant.SETTING_SERVICE);
		List<Setting> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				if(isTop){
					return "/findAllSettingMenuTop";
				}else{
					return "/findAllSettingMenuBottom";
				}
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				return map;
			}
		});
		return list;
	}
	
}
