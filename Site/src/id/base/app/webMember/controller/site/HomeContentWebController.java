package id.base.app.webMember.controller.site;

import id.base.app.ILookupConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@RequestMapping(value="/homeContent")
@Controller
public class HomeContentWebController {

	@RequestMapping(method=RequestMethod.GET, value="/getDataForHome")
	@ResponseBody
	public HashMap<String, Object> getDataForHome(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("housingIndexList", getHousingIndexList());
		return map;
	}
	
	private List<HousingIndex> getHousingIndexList(){
		RestCaller<HousingIndex> rc = new RestCaller<HousingIndex>(RestConstant.REST_SERVICE, RestServiceConstant.HOUSING_INDEX_SERVICE);
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		filters.add(new SearchFilter(HousingIndex.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		return rc.findAll(filters, orders);
	}
}
