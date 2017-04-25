package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.publication.LinkUrl;
import id.base.app.valueobject.testimonial.Testimonial;

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
		map.put("categoryHelpList", getCategoryHelpList());
		map.put("testimonialList", getTestimonialList());
		map.put("housingIndexList", getHousingIndexList());
		return map;
	}
	
	private List<Lookup> getCategoryHelpList(){
		LookupRestCaller lrc = new LookupRestCaller();
		return lrc.findByLookupGroup(ILookupGroupConstant.CATEGORY_HELP);
	}
	
	private List<LinkUrl> getHousingIndexList(){
		RestCaller<LinkUrl> rc = new RestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE);
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		filters.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		orders.add(new SearchOrder(LinkUrl.ORDER_NO, SearchOrder.Sort.ASC));
		return rc.findAll(filters, orders);
	}
	
	private List<Testimonial> getTestimonialList(){
		RestCaller<Testimonial> rc = new RestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		filters.add(new SearchFilter(Testimonial.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		orders.add(new SearchOrder(Testimonial.TESTIMONIAL_DATE, SearchOrder.Sort.DESC));
		return rc.findAll(filters, orders);
	}
}
