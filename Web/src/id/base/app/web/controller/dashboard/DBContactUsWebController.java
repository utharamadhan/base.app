package id.base.app.web.controller.dashboard;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.News;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Scope(value="request")
@Controller
@RequestMapping("/dashboard/contactUsStatistic")
public class DBContactUsWebController extends BaseController<News> {

	private final String PATH = "/dashboard/contactUsStatistic";
	
	@Override
	protected RestCaller<News> getRestCaller() {
		return new RestCaller<News>(RestConstant.REST_SERVICE, RestServiceConstant.NEWS_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="show")
	public String showDetail(@RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		return PATH;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {return null;}

	@Override
	protected List<SearchOrder> getSearchOrder() {return null;}

	@Override
	protected String getListPath() {return null;}
	
}
