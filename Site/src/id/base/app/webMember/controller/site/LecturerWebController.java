package id.base.app.webMember.controller.site;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.news.News;

@Scope(value="request")
@RequestMapping(value="/lecturer")
@Controller
public class LecturerWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(LecturerWebController.class);
	
	protected RestCaller<Tutor> getRestCaller() {
		return new RestCaller<Tutor>(RestConstant.REST_SERVICE, RestServiceConstant.TUTOR_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		List<Tutor> tutors = getRestCaller().findAll(filter, order);
		PagingWrapper<Tutor> pw = new PagingWrapper<Tutor>();
		pw.setResult(tutors);
		model.addAttribute("tutors", tutors);
		return "/lecturer/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="load")
	public String load(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		List<Tutor> tutors = getRestCaller().findAll(filter, order);
		PagingWrapper<Tutor> pw = new PagingWrapper<Tutor>();
		pw.setResult(tutors);
		model.addAttribute("tutors", tutors);
		return "/lecturer/main";
	}
	
}
