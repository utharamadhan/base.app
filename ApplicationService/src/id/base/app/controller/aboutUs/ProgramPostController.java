package id.base.app.controller.aboutUs;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.aboutUs.IProgramPostService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;

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
@RequestMapping(RestConstant.RM_PROGRAM_POST)
public class ProgramPostController extends SuperController<ProgramPost>{
	
	@Autowired
	private IProgramPostService programPostService;
	

	@Override
	public ProgramPost validate(ProgramPost anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ProgramPost.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(ProgramPost.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<ProgramPost> getMaintenanceService() {
		return programPostService;
	}
	
	@Override
	public ProgramPost preUpdate(ProgramPost anObject) throws SystemException{
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, ProgramPost newObject) {
		try {
			if(oldObject != null && oldObject instanceof Engagement && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (!((ProgramPost)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((ProgramPost)oldObject).getImageURL();
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
	public ProgramPost getOldObject(ProgramPost object) throws SystemException {
		ProgramPost oldObject = new ProgramPost();
		return object.getPkProgramPost() != null ? cloneObject(oldObject, findById(object.getPkProgramPost())) : null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatest")
	@ResponseBody
	public List<ProgramPost> findLatest(@RequestParam(value="number") int number) throws SystemException {
		try {
			return programPostService.findLatest(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
}
