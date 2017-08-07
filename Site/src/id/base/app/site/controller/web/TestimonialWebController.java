package id.base.app.site.controller.web;

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
import id.base.app.site.controller.BaseSiteController;
import id.base.app.valueobject.Faq;
import id.base.app.valueobject.advisory.AdvisoryConsulting;
import id.base.app.valueobject.testimonial.Testimonial;

@Scope(value="request")
@Controller
@RequestMapping(value="/testimonial")
public class TestimonialWebController extends BaseSiteController<Testimonial>{

	static Logger LOGGER = LoggerFactory.getLogger(TestimonialWebController.class);
	
	protected RestCaller<Testimonial> getRestCaller() {
		return new RestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{name}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,@PathVariable(value="id") Long id,
			@PathVariable(value="name") String name){
		Testimonial detail = getRestCaller().findById(id);
		if(detail!=null){
			setCommonData(request,model);
			model.addAttribute("detail", detail);
			return "/testimonial/detail";
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
}