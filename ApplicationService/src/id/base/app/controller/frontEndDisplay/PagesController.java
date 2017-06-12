package id.base.app.controller.frontEndDisplay;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontEndDisplay.IPagesService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.publication.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_PAGES)
public class PagesController extends SuperController<Pages>{
	
	@Autowired
	private IPagesService pagesService;
	

	@Override
	public Pages validate(Pages anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Pages.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			anObject.setPermalink(permalink);
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(Pages.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Pages> getMaintenanceService() {
		return pagesService;
	}
	
	@Override
	public Pages preUpdate(Pages anObject) throws SystemException{
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public Pages findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return pagesService.findByPermalink(permalink);
	}
	
}