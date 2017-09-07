package id.base.app.site.controller.web;

import id.base.app.paging.PagingWrapper;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="s", defaultValue="", required=false) String s){
		setCommonData(request,model);
		model.addAttribute("s", s);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(s)){
			filter.add(new SearchFilter(VWSearch.TITLE, Operator.LIKE, '%'+s+'%', String.class));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(VWSearch.ORDER_NO, Sort.ASC));
		order.add(new SearchOrder(VWSearch.TITLE, Sort.ASC));
		PagingWrapper<VWSearch> vwSearch = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("vwSearch", vwSearch);
		return "/search/main";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="s", defaultValue="", required=false) String s){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(s)){
			filter.add(new SearchFilter(VWSearch.TITLE, Operator.LIKE, '%'+s+'%', String.class));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(VWSearch.ORDER_NO, Sort.ASC));
		order.add(new SearchOrder(VWSearch.TITLE, Sort.ASC));
		PagingWrapper<VWSearch> vwSearch = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("vwSearch", vwSearch);
		return resultMap;
	}
}