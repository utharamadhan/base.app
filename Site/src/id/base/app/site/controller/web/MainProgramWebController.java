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

@Scope(value="request")
@RequestMapping(value="/mainProgram")
@Controller
public class MainProgramWebController {

	static Logger LOGGER = LoggerFactory.getLogger(MainProgramWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/main-program/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/learning")
	public String learning(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/main-program/seminar";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/seminar")
	public String seminar(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/main-program/seminar";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/workshop")
	public String workshop(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/main-program/workshop";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/kursus")
	public String kursus(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/main-program/kursus";
	}

}
