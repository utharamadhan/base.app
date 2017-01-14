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
import id.base.app.valueobject.aboutUs.ProgramPost;

@Scope(value="request")
@RequestMapping(value="/program")
@Controller
public class ProgramWebController2 {
	
	static Logger LOGGER = LoggerFactory.getLogger(ProgramWebController2.class);
	
	protected RestCaller<ProgramPost> getRestCaller() {
		return new RestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id
	){
		ProgramPost detail = ProgramPost.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/program/main";
	}
	
}
