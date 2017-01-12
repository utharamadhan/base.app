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
import id.base.app.valueobject.aboutUs.CommonPost;

@Scope(value="request")
@RequestMapping(value="/post")
@Controller
public class PostWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(PostWebController.class);
	
	protected RestCaller<CommonPost> getRestCaller() {
		return new RestCaller<CommonPost>(RestConstant.REST_SERVICE, RestServiceConstant.COMMON_POST_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id
	){
		CommonPost detail = CommonPost.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/post/main";
	}
	
}
