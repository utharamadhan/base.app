package id.base.app.controller.publication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.IDigitalBookService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.DigitalBook;

@RestController
@RequestMapping(RestConstant.RM_DIGITAL_BOOK)
public class DigitalBookController extends SuperController<DigitalBook>{
	
	@Autowired
	private IDigitalBookService digitalBookService;
	

	@Override
	public DigitalBook validate(DigitalBook anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(DigitalBook.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(DigitalBook.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getCoverImageURL())) {
			errorList.add(new ErrorHolder(DigitalBook.COVER_IMAGE_URL, messageSource.getMessage("error.mandatory", new String[]{"cover"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getFileURL())) {
			errorList.add(new ErrorHolder(DigitalBook.FILE_URL, messageSource.getMessage("error.mandatory", new String[]{"file"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(DigitalBook.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
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
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatestEbook")
	@ResponseBody
	public List<DigitalBook> findLatestEbook(@RequestParam(value="number") int number) throws SystemException {
		try {
			return digitalBookService.findLatestEbook(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
}
