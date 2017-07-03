package id.base.app.site.controller;

import id.base.app.SystemConstant;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;
import id.base.app.valueobject.publication.DigitalBook;
import id.base.app.valueobject.publication.Event;
import id.base.app.valueobject.publication.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Scope(value="request")
@Controller
public abstract class BaseSiteController<T> {
	
	protected void setMenu(ModelMap model){
		model.addAttribute("commonPostList", getCommonPostList());
		model.addAttribute("engagementList", getEngagementList());
		model.addAttribute("programList", getProgramList());
		model.addAttribute("digitalBookList", getDigitalBookList());
		model.addAttribute("newsList", getNewsList(5));
		model.addAttribute("eventList", getEventList());
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
}