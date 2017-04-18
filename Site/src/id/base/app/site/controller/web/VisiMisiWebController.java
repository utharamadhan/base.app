package id.base.app.site.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/visi")
@Controller
public class VisiMisiWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(VisiMisiWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response){
		return "/visi/main";
	}
	
}
