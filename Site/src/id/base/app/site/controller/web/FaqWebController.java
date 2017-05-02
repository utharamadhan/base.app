package id.base.app.site.controller.web;

import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.Faq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Controller
@RequestMapping(value="/faq")
public class FaqWebController {

	static Logger LOGGER = LoggerFactory.getLogger(FaqWebController.class);
	
	protected RestCaller<Faq> getRestCaller() {
		return new RestCaller<Faq>(RestConstant.REST_SERVICE, RestServiceConstant.FAQ_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("faq", findFaqListForView());
		return "/faq/main";
	}
	
	private List<Faq> findFaqListForView(){
		List<Faq> faqList = new ArrayList<>();
		faqList = (List<Faq>) new SpecificRestCaller(RestConstant.REST_SERVICE, RestServiceConstant.FAQ_SERVICE).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/findFaqListForView";
			}

			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<String, Object>();
				return map;
			}
		});
		return faqList;
	}
}