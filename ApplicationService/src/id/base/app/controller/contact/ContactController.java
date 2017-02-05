package id.base.app.controller.contact;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.contact.IContactService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.util.StringFunction;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.contact.Contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=RestConstant.RM_CONTACT)
public class ContactController extends SuperController<Contact> {
	
	public ContactController() {
		super();
	}
	
	@Autowired
	private ILookupService lookupService;
	@Autowired
	private IContactService contactService;

	@Override
	public MaintenanceService<Contact> getMaintenanceService() {
		return contactService;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateReturn")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public Long updateReturn(@RequestBody @Validated(UpdateEntity.class) Contact contact, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    getMaintenanceService().saveOrUpdate(preUpdate(contact));
	    return contact.getPkContact();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/submitReply")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public Long submitReply(@RequestBody @Validated(UpdateEntity.class) Contact contact, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    getMaintenanceService().saveOrUpdate(preReply(contact));
	    return contact.getPkContact();
	}
	
	public Contact preReply(Contact submitObj) throws SystemException {
		if(submitObj.getPkContact() != null || StringFunction.isEmpty(submitObj.getAnswer())) {
			Contact dbObj = contactService.findById(submitObj.getPkContact());
				dbObj.setAnswer(submitObj.getAnswer());
			return dbObj;
		}
		throw new SystemException(new ErrorHolder("ID is null"));
	}
	
	@Override
	public Contact preUpdate(Contact anObject) throws SystemException{
		return validate(anObject);
	}

	@Override
	public Contact validate(Contact anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getLatestContactUs")
	@ResponseBody
	public List<Contact> getLatestContactUs() throws SystemException {
		return contactService.getLatestContactUs();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countUnreadMessage")
	@ResponseBody
	public Integer countUnreadMessage() throws SystemException {
		return contactService.countUnreadMessage();
	}
	
}