package id.base.app.site.controller;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.MapRestCaller;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.AppParameter;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;
import id.base.app.valueobject.frontend.Setting;
import id.base.app.valueobject.publication.DigitalBook;
import id.base.app.valueobject.publication.Event;
import id.base.app.valueobject.publication.LinkUrl;
import id.base.app.valueobject.publication.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Scope(value="request")
@Controller
public abstract class BaseSiteController<T> {
	
	protected void setCommonData(HttpServletRequest request, ModelMap model){
		setDataForScript(request, model);
		model.addAttribute("commonPostList", getCommonPostList());
		model.addAttribute("engagementList", getEngagementList());
		model.addAttribute("programList", getProgramList());
		model.addAttribute("digitalBookList", getDigitalBookList());
		model.addAttribute("newsList", getNewsList(5));
		model.addAttribute("eventList", getEventList());
		model.addAttribute("linkUrlList", getLinkUrlList());
		model.addAttribute("menuTop", getMenuSettingList(true));
		model.addAttribute("menuBottom", getMenuSettingList(false));
		model.addAttribute("socialMedia", getSocialMediaList());
		List<Pages> pages = getTocLink();
		if(pages!=null && !pages.isEmpty()){
			request.setAttribute("toc", pages.get(0).getPermalink());
			request.setAttribute("tocTitle", pages.get(0).getTitle());
		}
	}
	
	private void setDataForScript(HttpServletRequest request, ModelMap model){
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("url", request.getRequestURL().toString());
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = new MapRestCaller<String, Object>(RestConstant.REST_SERVICE, RestServiceConstant.INTEGRATION_SCRIPT_SERVICE).executePostMap(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findByUrl";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return map;
			}
		}, HashMap.class);
		
		if(resultMap!=null){
			if(resultMap.get("header")!=null){
				model.addAttribute("headerScript", resultMap.get("header"));		
			}
			if(resultMap.get("footer")!=null){
				model.addAttribute("footerScript", resultMap.get("footer"));		
			}
		}
	}
	
	private List<Pages> getCommonPostList(){
		SpecificRestCaller<Pages> rcPages = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
		return rcPages.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleData";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.PagesType.ABOUT_US);
				return map;
			}
		});
	}
			 
	private List<Engagement> getEngagementList(){
		SpecificRestCaller<Engagement> rcEngagement = new SpecificRestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
		return rcEngagement.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatest";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", 5);
				return map;
			}
		});
	}
	
	private List<ProgramPost> getProgramList(){
		SpecificRestCaller<ProgramPost> rcProgram = new SpecificRestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
		return rcProgram.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatest";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", 5);
				return map;
			}
		});
	}
			
	private List<DigitalBook> getDigitalBookList(){
		SpecificRestCaller<DigitalBook> rcEbook = new SpecificRestCaller<DigitalBook>(RestConstant.REST_SERVICE, RestServiceConstant.DIGITAL_BOOK_SERVICE);
		return rcEbook.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatestEbook";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", 5);
				return map;
			}
		});
	}
	
	private List<News> getNewsList(final int number){
		SpecificRestCaller<News> rcNews = new SpecificRestCaller<News>(RestConstant.REST_SERVICE, RestServiceConstant.NEWS_SERVICE);
		return rcNews.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatestNews";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", number);
				return map;
			}
		});
	}
			
	private List<Event> getEventList(){
		SpecificRestCaller<Event> rcEvent = new SpecificRestCaller<Event>(RestConstant.REST_SERVICE, RestServiceConstant.EVENT_SERVICE);
		return rcEvent.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatestEventUpcoming";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", 5);
				return map;
			}
		});
	}
	
	private List<LinkUrl> getLinkUrlList(){
		RestCaller<LinkUrl> restCallFLU = new RestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE);
		List<SearchFilter> filterFLU = new ArrayList<SearchFilter>();
		filterFLU.add(new SearchFilter(LinkUrl.TYPE, Operator.EQUALS, SystemConstant.LinkUrlType.FOOTER, String.class));
		filterFLU.add(new SearchFilter(LinkUrl.FK_LINK_URL_PARENT, Operator.IS_NULL, null));
		filterFLU.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		List<SearchOrder> orderFLU = new ArrayList<SearchOrder>();
		orderFLU.add(new SearchOrder(LinkUrl.ORDER_NO, Sort.ASC));
		return restCallFLU.findAll(filterFLU, orderFLU);
	}
	
	private List<Pages> getTocLink(){
		SpecificRestCaller<Pages> rcPages = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
		return rcPages.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getTocLink";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<>();
			}
		});
	}
	
	private List<Setting> getMenuSettingList(final Boolean isTop){
		SpecificRestCaller<Setting> rc = new SpecificRestCaller<Setting>(RestConstant.REST_SERVICE, RestServiceConstant.SETTING_SERVICE);
		return rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				if(isTop){
					return "/findAllSettingMenuTop";
				}else{
					return "/findAllSettingMenuBottom";
				}
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<>();
			}
		});
	}
	
	private List<AppParameter> getSocialMediaList(){
		SpecificRestCaller<AppParameter> rc = new SpecificRestCaller<AppParameter>(RestConstant.REST_SERVICE, RestServiceConstant.SYSTEM_PARAMETER_SERVICE);
		return rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getAllSocialMedia";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<>();
			}
		});
	}
}