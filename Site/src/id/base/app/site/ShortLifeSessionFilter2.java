package id.base.app.site;

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
import id.base.app.valueobject.frontend.HomeSettingHelper;
import id.base.app.valueobject.frontend.Setting;
import id.base.app.valueobject.publication.DigitalBook;
import id.base.app.valueobject.publication.Event;
import id.base.app.valueobject.publication.HousingIndex;
import id.base.app.valueobject.publication.LinkUrl;
import id.base.app.valueobject.publication.News;
import id.base.app.valueobject.testimonial.Testimonial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class ShortLifeSessionFilter2 implements Filter{
	
	private static final Set<String> BYPASS_TOKEN = new HashSet<String>();
	static{
		BYPASS_TOKEN.add("/page/landingPage");
		BYPASS_TOKEN.add("/page/landingPage/loginPost");
		BYPASS_TOKEN.add("/page/landingPage/blank");
		BYPASS_TOKEN.add("/page/token/tokenExpired");
		BYPASS_TOKEN.add("/page/token/tokenInvalid");
		BYPASS_TOKEN.add("/page/registration");
		BYPASS_TOKEN.add("/page/registration/submit");
		BYPASS_TOKEN.add("/page/login/loginFromActivation");
		BYPASS_TOKEN.add("/page/registration/activationPage");
		BYPASS_TOKEN.add("/page/registration/activationSubmit");
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if("/index.jsp".equalsIgnoreCase(request.getServletPath())){
			setDataForScript(request);
			setDataForHome(request);
		}
		Cookie cookieValue = getCookie(request.getCookies(), request);
		if(cookieValue!=null){	
			chain.doFilter(request, response);
		}else{
			String url = request.getContextPath().replace("Site", "Auth");
			response.sendRedirect(url);
			return;
		}
	}
	
	private void setDataForScript(HttpServletRequest request){
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
				request.setAttribute("headerScript", resultMap.get("header"));		
			}
			if(resultMap.get("footer")!=null){
				request.setAttribute("footerScript", resultMap.get("footer"));		
			}
		}
	}
	
	private void setDataForHome(HttpServletRequest request){
		request.setAttribute("housingIndexList", getHousingIndexList());
		request.setAttribute("newsHomeList", getNewsList(9));
		request.setAttribute("testimonialHomeList", getTestimonialList());
		request.setAttribute("commonPostList", getCommonPostList());
		request.setAttribute("engagementList", getEngagementList());
		request.setAttribute("programList", getProgramList());
		request.setAttribute("digitalBookList", getDigitalBookList());
		request.setAttribute("newsList", getNewsList(5));
		request.setAttribute("eventList", getEventList());
		request.setAttribute("linkUrlList", getLinkUrlList());
		request.setAttribute("menuTop", getMenuSettingList(true));
		request.setAttribute("menuBottom", getMenuSettingList(false));
		request.setAttribute("socialMedia", getSocialMediaList());
		request.setAttribute("home", getHomeSettingList());
		List<Pages> pages = getTocAndPilarLink();
		for (Pages p : pages) {
			if(SystemConstant.PagesType.PILAR.equals(p.getType())){
				request.setAttribute("pilar", p.getPermalink());
			}else if(SystemConstant.PagesType.TERM_CONDITION.equals(p.getType())){
				request.setAttribute("toc", p.getPermalink());
				request.setAttribute("tocTitle", p.getTitle());
			}
		}
	}
	
	private List<HousingIndex> getHousingIndexList(){
		SpecificRestCaller<HousingIndex> rcHI = new SpecificRestCaller<HousingIndex>(RestConstant.REST_SERVICE, RestServiceConstant.HOUSING_INDEX_SERVICE);
		return rcHI.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleData";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				return map;
			}
		});
	}
	
	private List<Testimonial> getTestimonialList(){
		SpecificRestCaller<Testimonial> rcEngagement = new SpecificRestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
		return rcEngagement.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findLatest";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("number", 14);
				return map;
			}
		});
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
	
	private List<Pages> getTocAndPilarLink(){
		SpecificRestCaller<Pages> rcPages = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
		return rcPages.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getTocAndPilarLink";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				return new HashMap<>();
			}
		});
	}
	
	private HomeSettingHelper getHomeSettingList(){
		SpecificRestCaller<HomeSettingHelper> rc = new SpecificRestCaller<HomeSettingHelper>(RestConstant.REST_SERVICE, RestServiceConstant.HOME_SETTING_SERVICE);
		return rc.executeGet(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findAllSettingHome";
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
	
	private Cookie getCookie(Cookie[] cookies, HttpServletRequest request) {
		Cookie cookie = null;
		if(cookies!=null){
			for (Cookie cook : cookies) {
				if(cook.getName().equals("AUTH_KEY")){
					cookie = cook;
					break;
				}
			}
		}
		return cookie;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
