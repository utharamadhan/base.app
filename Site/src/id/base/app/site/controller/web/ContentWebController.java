package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.aboutUs.CommonPost;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;
import id.base.app.valueobject.publication.DigitalBook;
import id.base.app.valueobject.publication.Event;
import id.base.app.valueobject.publication.LinkUrl;
import id.base.app.valueobject.publication.News;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@RequestMapping(value="/content")
@Controller
public class ContentWebController {

	@RequestMapping(method=RequestMethod.GET, value="/getDataForHome")
	@ResponseBody
	public HashMap<String, Object> getDataForHome(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("categoryHelpList", getCategoryHelpList());
		map.put("testimonialList", getTestimonialList());
		map.put("housingIndexList", getHousingIndexList());
		map.put("newsList", getNewsList(9));
		return map;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getData")
	@ResponseBody
	public HashMap<String, Object> getData(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("commonPostList", getCommonPostList());
		map.put("engagementList", getEngagementList());
		map.put("programList", getProgramList());
		map.put("digitalBookList", getDigitalBookList());
		map.put("newsList", getNewsList(5));
		map.put("eventList", getEventList());
		map.put("linkUrlList", getLinkUrlList());
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
	
	private List<CommonPost> getCommonPostList(){
		RestCaller<CommonPost> restCall = new RestCaller<CommonPost>(RestConstant.REST_SERVICE, RestServiceConstant.COMMON_POST_SERVICE);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(CommonPost.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID, Integer.class));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(CommonPost.PK_COMMON_POST, Sort.ASC));
		return restCall.findAll(filter, order);
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
		filterFLU.add(new SearchFilter(LinkUrl.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH, Integer.class));
		List<SearchOrder> orderFLU = new ArrayList<SearchOrder>();
		orderFLU.add(new SearchOrder(LinkUrl.ORDER_NO, Sort.ASC));
		return restCallFLU.findAll(filterFLU, orderFLU);
	}
}
