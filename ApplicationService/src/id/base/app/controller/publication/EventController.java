package id.base.app.controller.publication;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.event.IEventService;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.Event;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

@RestController
@RequestMapping(RestConstant.RM_EVENT)
public class EventController extends SuperController<Event>{
	
	@Autowired
	private IEventService eventService;
	

	@Override
	public Event validate(Event anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Event.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = eventService.getSamePermalink(anObject.getPkEvent(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Event> getMaintenanceService() {
		return eventService;
	}
	
	@Override
	public Event preUpdate(Event anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, Event newObject) {
		try {
			if(oldObject != null && oldObject instanceof Event && newObject != null && StringFunction.isNotEmpty(newObject.getCoverImageURL())) {
				if (!((Event)oldObject).getCoverImageURL().equalsIgnoreCase(newObject.getCoverImageURL())) {
					String oldURL = ((Event)oldObject).getCoverImageURL();
					String fileSystemURL = SystemConstant.FILE_STORAGE + oldURL.substring(SystemConstant.IMAGE_SHARING_URL.length(), oldURL.length());
					Path path = Paths.get(fileSystemURL);
					Files.deleteIfExists(path);
				}
			}	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Event getOldObject(Event object) throws SystemException {
		Event oldObject = new Event();
		return object.getPkEvent() != null ? cloneObject(oldObject, findById(object.getPkEvent())) : null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findArchived")
	@ResponseBody
	public List<Event> findArchived(@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson) throws SystemException {
		try {
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			if(StringUtils.isNotEmpty(filterJson)){
				filter = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
			}
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			if(StringUtils.isNotEmpty(orderJson)){
				order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			}
			return eventService.findArchived(filter, order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findUpcoming")
	@ResponseBody
	public List<Event> findUpcoming(@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam(value="order", defaultValue="", required=false) String orderJson, @RequestParam Map<String,String> params) throws SystemException {
		try {
			List<SearchFilter> filter = new ArrayList<SearchFilter>();
			if(StringUtils.isNotEmpty(filterJson)){
				filter = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
			}
			List<SearchOrder> order = new ArrayList<SearchOrder>();
			if(StringUtils.isNotEmpty(orderJson)){
				order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			}
			Date startDate = DateTimeFunction.string2Date(params.get("start"), SystemConstant.WEB_SERVICE_DATE);
			Date endDate = DateTimeFunction.string2Date(params.get("end"), SystemConstant.WEB_SERVICE_DATE);
			filter.add(new SearchFilter(Event.EVENT_DATE, Operator.EQUALS_OR_GREATER_THAN, startDate));
			filter.add(new SearchFilter(Event.EVENT_DATE, Operator.EQUALS_OR_LESS_THAN, endDate));
			return eventService.findUpcoming(filter, order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatestEventUpcoming")
	@ResponseBody
	public List<Event> findLatestEventUpcoming(@RequestParam(value="number") int number) throws SystemException {
		try {
			return eventService.findLatestEventUpcoming(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public Event findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return eventService.findByPermalink(permalink);
	}
	
}
