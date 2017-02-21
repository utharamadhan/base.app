package id.base.app.webMember.controller.site;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.QueryParamInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.event.Event;
import id.base.app.valueobject.event.EventJson;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Scope(value="request")
@RequestMapping(value="/events")
@Controller
public class EventsWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(EventsWebController.class);
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected RestCaller<Event> getRestCaller() {
		return new RestCaller<Event>(RestConstant.REST_SERVICE, RestServiceConstant.EVENT_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/upcoming")
	public String upcoming(HttpServletRequest request, HttpServletResponse response){
		return "/events/upcoming/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/archived")
	public String archived(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		filter.add(new SearchFilter(Event.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH));
		final String filterJson = mapper.writeValueAsString(filter);
		final String orderJson = mapper.writeValueAsString(order);
		SpecificRestCaller<Event> eventRC = new SpecificRestCaller<Event>(RestConstant.REST_SERVICE, RestServiceConstant.EVENT_SERVICE);
		List<Event> events = eventRC.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findArchived";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("filter", filterJson);
				map.put("order", orderJson);
				return map;
			}
		});
		model.addAttribute("events", events);
		return "/events/archived/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/loadEvents")
	@ResponseBody
	public ArrayNode loadEvents(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params) throws JsonProcessingException{
		ArrayNode jsonData = null;
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		filter.add(new SearchFilter(Event.STATUS, Operator.EQUALS, ILookupConstant.ArticleStatus.PUBLISH));
		final Map<String,String> param = params; 
		final String filterJson = mapper.writeValueAsString(filter);
		final String orderJson = mapper.writeValueAsString(order);
		SpecificRestCaller<Event> eventRC = new SpecificRestCaller<Event>(RestConstant.REST_SERVICE, RestServiceConstant.EVENT_SERVICE);
		List<Event> events = eventRC.executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findUpcoming";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("filter", filterJson);
				map.put("order", orderJson);
				map.put("start", param.get("start"));
				map.put("end", param.get("end"));
				return map;
			}
		});
		List<EventJson> jsons = new ArrayList<EventJson>();
		for(Event e : events){
			EventJson json = new EventJson();
			json.setTitle(e.getTitle());
			json.setEventDate(DateTimeFunction.date2String(e.getEventDate(), SystemConstant.WEB_SERVICE_DATE));
			String url = request.getContextPath() + "/page/events/detail/" + e.getPkEvent() + "?f=upcoming";
			json.setUrl(url);
			jsons.add(json);
		}
		jsonData = mapper.valueToTree(jsons);
		return jsonData;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id, @RequestParam Map<String,String> params
	){
		Event detail = Event.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		model.addAttribute("f", params.get("f"));
		return "/events/detail";
	}
}
