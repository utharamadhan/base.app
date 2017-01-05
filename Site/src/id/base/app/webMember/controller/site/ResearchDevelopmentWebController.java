package id.base.app.webMember.controller.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/research-development")
@Controller
public class ResearchDevelopmentWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(ResearchDevelopmentWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response){
		return this.list(request, response);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		return "/research-development/list";
	}
	
}
