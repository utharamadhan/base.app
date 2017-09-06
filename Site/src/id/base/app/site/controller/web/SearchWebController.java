package id.base.app.site.controller.web;

import id.base.app.SystemConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.VWSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Scope(value="request")
@RequestMapping(value="/search")
@Controller
public class SearchWebController extends BaseSiteController<VWSearch>{

	static Logger LOGGER = LoggerFactory.getLogger(SearchWebController.class);

	protected RestCaller<VWSearch> getRestCaller() {
		return new RestCaller<VWSearch>(RestConstant.REST_SERVICE, RestServiceConstant.VW_SEARCH_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="s", required=false) String s){
		setCommonData(request,model);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(s)){
			filter.add(new SearchFilter(VWSearch.TITLE, Operator.LIKE, '%'+s+'%', String.class));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(VWSearch.ORDER_NO, Sort.ASC));
		order.add(new SearchOrder(VWSearch.TITLE, Sort.ASC));
		Map<String, List<VWSearch>> map = new HashMap<>();
		map.put(SystemConstant.SearchType.ENGAGEMENT, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.PROGRAM_POST, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.DIGITAL_BOOK, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.NEWS, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.EVENT, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.LEARNING, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.ADVISORY, new ArrayList<VWSearch>());
		map.put(SystemConstant.SearchType.RESEARCH, new ArrayList<VWSearch>());
		List<VWSearch> searches = getRestCaller().findAll(filter, order);
		for (VWSearch vwSearch : searches) {
			List<VWSearch> list = map.get(vwSearch.getType());
			list.add(vwSearch);
		}
		for (Map.Entry<String, List<VWSearch>> entry : map.entrySet()) {
			model.addAttribute(entry.getKey(), entry.getValue());
		}
		return "/search/main";
	}	
}