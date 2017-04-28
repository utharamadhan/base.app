package id.base.app.controller.aboutUs;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.aboutUs.IEngagementService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Engagement;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_ENGAGEMENT)
public class EngagementController extends SuperController<Engagement>{
	
	@Autowired
	private IEngagementService engagementService;
	

	@Override
	public Engagement validate(Engagement anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Engagement.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(Engagement.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Engagement> getMaintenanceService() {
		return engagementService;
	}
	
	@Override
	public Engagement preUpdate(Engagement anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, Engagement newObject) {
		try {
			if(oldObject != null && oldObject instanceof Engagement && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (!((Engagement)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((Engagement)oldObject).getImageURL();
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
	public Engagement getOldObject(Engagement object) throws SystemException {
		Engagement oldObject = new Engagement();
		return object.getPkEngagement() != null ? cloneObject(oldObject, findById(object.getPkEngagement())) : null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatest")
	@ResponseBody
	public List<Engagement> findLatest(@RequestParam(value="number") int number) throws SystemException {
		try {
			return engagementService.findLatest(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
}
