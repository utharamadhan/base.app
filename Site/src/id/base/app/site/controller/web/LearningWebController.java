package id.base.app.site.controller.web;

import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.valueobject.learning.LearningItem;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/main-program/learning")
@Controller
public class LearningWebController extends BaseSiteController<LearningItem>{
	
	static Logger LOGGER = LoggerFactory.getLogger(LearningWebController.class);
	
	protected RestCaller<LearningItem> getRestCallerCourse() {
		return new RestCaller<LearningItem>(RestConstant.REST_SERVICE, RestServiceConstant.LEARNING_ITEM_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "redirect:/page/main-program/learning/"+getFirstPermalinkData();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink){
		setMenu(model);
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("methodOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_METHOD));
		model.addAttribute("organizerOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_ORGANIZER));
		model.addAttribute("paymentOptions", lrc.findByLookupGroup(ILookupGroupConstant.LEARNING_PAYMENT));
		return "/learning/detail";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
	private String getFirstPermalinkData() throws SystemException {
		return new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_CATEGORY, String.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getFirstPermalinkData/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.LEARNING);
				return map;
			}
		});
	}	
}