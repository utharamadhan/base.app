package id.base.app.site.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.valueobject.Faq;

@Scope(value="request")
@Controller
@RequestMapping(value="/sitemap")
public class SiteMapWebController extends BaseSiteController<Faq>{

	static Logger LOGGER = LoggerFactory.getLogger(SiteMapWebController.class);
	
	protected RestCaller<Faq> getRestCaller() {
		return new RestCaller<Faq>(RestConstant.REST_SERVICE, RestServiceConstant.FAQ_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setCommonData(request,model);
		return "/sitemap/main";
	}
	
}