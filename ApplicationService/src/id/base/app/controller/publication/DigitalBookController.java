package id.base.app.controller.publication;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.IDigitalBookService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.DigitalBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_DIGITAL_BOOK)
public class DigitalBookController extends SuperController<DigitalBook>{
	
	@Autowired
	private IDigitalBookService digitalBookService;
	

	@Override
	public DigitalBook validate(DigitalBook anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(DigitalBook.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(DigitalBook.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(DigitalBook.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getLink())) {
			errorList.add(new ErrorHolder(DigitalBook.LINK, messageSource.getMessage("error.mandatory", new String[]{"link"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<DigitalBook> getMaintenanceService() {
		return digitalBookService;
	}
	
	@Override
	public DigitalBook preUpdate(DigitalBook anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
