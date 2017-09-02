package id.base.app.web.controller.frontEndDisplay;

import id.base.app.valueobject.publication.News;

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
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		/*News detail = getRestCaller().findById(maintenancePK);
		model.addAttribute("detail", detail);*/
		return PATH_DETAIL;
	}
	
}
