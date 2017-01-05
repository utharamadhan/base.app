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
@RequestMapping(value="/events")
@Controller
public class EventsWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(EventsWebController.class);
	
	@RequestMapping(method=RequestMethod.GET, value="/upcoming")
	public String upcoming(HttpServletRequest request, HttpServletResponse response){
		return "/events/upcoming/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/archived")
	public String archived(HttpServletRequest request, HttpServletResponse response){
		return "/events/archived/main";
	}
	
}
