package id.base.app.controller.event;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.event.IEventService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_EVENT)
public class EventController extends SuperController<Event>{
	
	@Autowired
	private IEventService eventService;
	

	@Override
	public Event validate(Event anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(Event.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())) {
			errorList.add(new ErrorHolder(Event.NAME, messageSource.getMessage("error.mandatory", new String[]{"name"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(Event.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
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
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
