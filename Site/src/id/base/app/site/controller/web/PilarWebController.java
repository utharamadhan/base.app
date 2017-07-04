package id.base.app.site.controller.web;

import id.base.app.site.controller.BaseSiteController;
import id.base.app.valueobject.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/pilar")
@Controller
public class PilarWebController extends BaseSiteController<Pages>{

	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setMenu(model);
		return "/pilar/detail";
	}
}