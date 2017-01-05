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
@RequestMapping(value="/learning")
@Controller
public class LearningWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(LearningWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response){
		return "/learning/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response){
		return "/learning/detail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/program")
	public String program(HttpServletRequest request, HttpServletResponse response){
		return "/learning/program";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/programDetail")
	public String programDetail(HttpServletRequest request, HttpServletResponse response){
		return "/learning/program_detail";
	}
	
}
