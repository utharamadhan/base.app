package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.ILinkUrlService;
import id.base.app.valueobject.publication.LinkUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
		}
		
		if(anObject.getUrl() == null){
			errors.add(new ErrorHolder("Url is Mandatory"));
		}
		
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
}