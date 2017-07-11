package id.base.app.site;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShortLifeSessionFilter2 implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(ShortLifeSessionFilter2.class);
	
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
		List<HousingIndex> hi = rcHI.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return hi;
	}
	
	private List<Testimonial> getTestimonialList(){
		SpecificRestCaller<Testimonial> rcEngagement = new SpecificRestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
		List<Testimonial> testimonials = rcEngagement.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return testimonials;
	}
	
	private List<Pages> getCommonPostList(){
		SpecificRestCaller<Pages> rcPages = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
		List<Pages> pages = rcPages.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return pages;
	}
			 
	private List<Engagement> getEngagementList(){
		SpecificRestCaller<Engagement> rcEngagement = new SpecificRestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
		List<Engagement> engages = rcEngagement.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return engages;
	}
	
	private List<ProgramPost> getProgramList(){
		SpecificRestCaller<ProgramPost> rcProgram = new SpecificRestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
		List<ProgramPost> programs = rcProgram.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return programs;
	}
			
	private List<DigitalBook> getDigitalBookList(){
		SpecificRestCaller<DigitalBook> rcEbook = new SpecificRestCaller<DigitalBook>(RestConstant.REST_SERVICE, RestServiceConstant.DIGITAL_BOOK_SERVICE);
		List<DigitalBook> ebooks = rcEbook.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return ebooks;
	}
	
	private List<News> getNewsList(final int number){
		SpecificRestCaller<News> rcNews = new SpecificRestCaller<News>(RestConstant.REST_SERVICE, RestServiceConstant.NEWS_SERVICE);
		List<News> news = rcNews.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return news;
	}
			
	private List<Event> getEventList(){
		SpecificRestCaller<Event> rcEvent = new SpecificRestCaller<Event>(RestConstant.REST_SERVICE, RestServiceConstant.EVENT_SERVICE);
		List<Event> events = rcEvent.executeGetList(new QueryParamInterfaceRestCaller() {
			
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
		return events;
	}
			
	private List<LinkUrl> getLinkUrlList(){
		RestCaller<LinkUrl> restCallFLU = new RestCaller<LinkUrl>(RestConstant.REST_SERVICE, RestServiceConstant.LINK_URL_SERVICE);
		List<SearchFilter> filterFLU = new ArrayList<SearchFilter>();
		filterFLU.add(new SearchFilter(LinkUrl.TYPE, Operator.EQUALS, SystemConstant.LinkUrlType.FOOTER, String.class));
		filterFLU.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		List<SearchOrder> orderFLU = new ArrayList<SearchOrder>();
		orderFLU.add(new SearchOrder(LinkUrl.ORDER_NO, Sort.ASC));
		return restCallFLU.findAll(filterFLU, orderFLU);
	}
	
	private List<Pages> getTocAndPilarLink(){
		SpecificRestCaller<Pages> rcPages = new SpecificRestCaller<Pages>(RestConstant.REST_SERVICE, RestServiceConstant.PAGES_SERVICE);
		List<Pages> pages = rcPages.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/getTocAndPilarLink";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				return map;
			}
		});
		return pages;
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
