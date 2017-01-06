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

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.news.News;

@Scope(value="request")
@RequestMapping(value="/news")
@Controller
public class NewsWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(NewsWebController.class);
	
	protected RestCaller<News> getRestCaller() {
		return new RestCaller<News>(RestConstant.REST_SERVICE, RestServiceConstant.NEWS_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		List<News> news = getRestCaller().findAll(filter, order);
		model.addAttribute("news", news);
		return "/news/main";
	}
	
}
