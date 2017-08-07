package id.base.app.site.controller.web;

import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.valueobject.Pages;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/about-us")
@Controller
public class AboutUsWebController extends BaseSiteController<Pages>{
	
	static Logger LOGGER = LoggerFactory.getLogger(AboutUsWebController.class);
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink){
		setCommonData(request,model);
		Pages detail = findByPermalink(permalink);
		if(detail!=null){
			if(detail.getTitle()!=null){
				model.addAttribute("detail", detail);
				return "/about-us/main";
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	private Pages findByPermalink(final String permalink){
		Pages detail = new Pages();
		try{
			detail = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestConstant.RM_PAGES, Pages.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/findByPermalink/{permalink}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("permalink", permalink);
					return map;
				}
			});
			
		}catch(Exception e){
			detail = null;
		}
		return detail;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}	
}