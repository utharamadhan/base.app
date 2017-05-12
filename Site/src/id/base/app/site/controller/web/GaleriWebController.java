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
@RequestMapping(value="/galeri")
@Controller
public class GaleriWebController {

static Logger LOGGER = LoggerFactory.getLogger(MainProgramWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/galeri/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail")
	public String learning(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/galeri/detail";
	}
	
}