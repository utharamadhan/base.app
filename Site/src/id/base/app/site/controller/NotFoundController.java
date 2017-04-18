package id.base.app.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author USER
 *
 */
@Scope(value="request")
@Controller
@RequestMapping(value="/notfound")
public class NotFoundController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String landingPagePost(HttpServletRequest request, HttpServletResponse response) {
		return "/notfound";
	}
	
}