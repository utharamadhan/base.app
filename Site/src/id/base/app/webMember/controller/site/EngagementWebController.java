package id.base.app.webMember.controller.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.valueobject.aboutUs.Engagement;

@Scope(value="request")
@RequestMapping(value="/engagement")
@Controller
public class EngagementWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(EngagementWebController.class);
	
	protected RestCaller<Engagement> getRestCaller() {
		return new RestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id
	){
		Engagement detail = Engagement.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/engagement/main";
	}
	
}
