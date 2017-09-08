package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.ILinkUrlService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.LinkUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_LINK_URL)
public class LinkUrlController extends SuperController<LinkUrl>{

	@Autowired
	private ILinkUrlService linkUrlService;
	
	@Override
	public MaintenanceService<LinkUrl> getMaintenanceService() {
		return linkUrlService;
	}

	@Override
	public LinkUrl validate(LinkUrl anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getTitle() == null){
			errors.add(new ErrorHolder("Title is Mandatory"));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = linkUrlService.getSamePermalink(anObject.getPkLinkUrl(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		
		if(!anObject.getIsParent() && anObject.getUrl() == null){
			errors.add(new ErrorHolder("Url is Mandatory"));
		}
		
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@Override
	public LinkUrl preUpdate(LinkUrl anObject) throws SystemException{
		if(anObject.getIsParent()){
			anObject.setFkLinkUrlParent(null);
			anObject.setUrl(null);
		}
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getPkByPermalink/{permalink}")
	@ResponseBody
	public Long getPkByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return linkUrlService.getPkByPermalink(permalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getTitleByPermalink/{permalink}")
	@ResponseBody
	public String getTitleByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return linkUrlService.getTitleByPermalink(permalink);
	}
	
}