package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.publication.LinkUrl;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@RequestMapping(value="/other-site")
@Controller
public class OtherSiteWebController extends BaseSiteController<LinkUrl>{
	
	static Logger LOGGER = LoggerFactory.getLogger(SearchWebController.class);

	protected RestCaller<LinkUrl> getRestCaller() {
		return new RestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="permalink") String permalink){
		setCommonData(request,model);
		model.addAttribute("title", getTitleByPermalink(permalink));
		Long pk = getPkByPermalink(permalink);
		List<SearchFilter> f = new ArrayList<SearchFilter>();
		List<SearchOrder> o = new ArrayList<SearchOrder>();
		f.add(new SearchFilter(LinkUrl.FK_LINK_URL_PARENT, Operator.EQUALS, pk, Long.class));
		f.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH));
		o.add(new SearchOrder(LinkUrl.ORDER_NO, Sort.ASC));
		o.add(new SearchOrder(LinkUrl.TITLE, Sort.ASC));
		model.addAttribute("list", getRestCaller().findAll(f, o));
		return "/other-site/detail";
	}
	
	private Long getPkByPermalink(final String permalink){
		SpecificRestCaller<Long> str = new SpecificRestCaller<Long>(RestConstant.REST_SERVICE, RestConstant.RM_LINK_URL, Long.class);
		return str.executeGet(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getPkByPermalink/{permalink}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("permalink", permalink);
				return map;
			}
		});
	}
	
	private String getTitleByPermalink(final String permalink){
		SpecificRestCaller<String> str = new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_LINK_URL, String.class);
		return str.executeGet(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getTitleByPermalink/{permalink}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("permalink", permalink);
				return map;
			}
		});
	}
	
}