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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_DIGITAL_BOOK)
public class DigitalBookController extends SuperController<DigitalBook>{
	
	@Autowired
	private IDigitalBookService digitalBookService;
	

	@Override
	public DigitalBook validate(DigitalBook anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(DigitalBook.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = digitalBookService.getSamePermalink(anObject.getPkDigitalBook(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
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
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, DigitalBook newObject) {
		try {
			if(oldObject != null && oldObject instanceof DigitalBook && newObject != null) {
				if(StringFunction.isNotEmpty(newObject.getCoverImageURL())) {					
					if (!((DigitalBook)oldObject).getCoverImageURL().equalsIgnoreCase(newObject.getCoverImageURL())) {
						String oldURL = ((DigitalBook)oldObject).getCoverImageURL();
						String fileSystemURL = SystemConstant.FILE_STORAGE + oldURL.substring(SystemConstant.IMAGE_SHARING_URL.length(), oldURL.length());
						Path path = Paths.get(fileSystemURL);
						Files.deleteIfExists(path);
					}
				}
				if(StringFunction.isNotEmpty(newObject.getFileURL())) {					
					if (!((DigitalBook)oldObject).getFileURL().equalsIgnoreCase(newObject.getFileURL())) {
						String oldURL = ((DigitalBook)oldObject).getFileURL();
						String fileSystemURL = SystemConstant.FILE_STORAGE + oldURL.substring(SystemConstant.IMAGE_SHARING_URL.length(), oldURL.length());
						Path path = Paths.get(fileSystemURL);
						Files.deleteIfExists(path);
					}
				}
			}	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public DigitalBook getOldObject(DigitalBook object) throws SystemException {
		DigitalBook oldObject = DigitalBook.getInstance();
		return object.getPkDigitalBook() != null ? cloneObject(oldObject, findById(object.getPkDigitalBook())) : null;
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
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public DigitalBook findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return digitalBookService.findByPermalink(permalink);
	}
	
}
