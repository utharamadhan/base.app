package id.base.app.site.controller.web;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.valueobject.Pages;

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
@RequestMapping(value="/post")
@Controller
public class PostWebController extends BaseSiteController<Pages>{
	
	static Logger LOGGER = LoggerFactory.getLogger(PostWebController.class);
	
	protected RestCaller<Pages> getRestCaller() {
		return new RestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/{title}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id,
			@PathVariable(value="title") String title
	){
		Pages detail = Pages.getInstance();
		detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getTitle()!=null){
				String dataTitle = detail.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					setMenu(model);
					model.addAttribute("detail", detail);
					return "/post/main";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
}
