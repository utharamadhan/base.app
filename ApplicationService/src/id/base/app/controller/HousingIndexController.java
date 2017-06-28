package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.IHousingIndexService;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_HOUSING_INDEX)
public class HousingIndexController extends SuperController<HousingIndex>{

	@Autowired
	private IHousingIndexService housingIndexService;
	
	@Override
	public MaintenanceService<HousingIndex> getMaintenanceService() {
		return housingIndexService;
	}

	@Override
	public HousingIndex validate(HousingIndex anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getTitle() == null){
			errors.add(new ErrorHolder("Title is Mandatory"));
		}
		
		if(anObject.getValue() == null){
			errors.add(new ErrorHolder("Value is Mandatory"));
		}
		
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@Override
	public HousingIndex preUpdate(HousingIndex anObject) throws SystemException{
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getLinkUrlDetail")
	@ResponseBody
	public String getLinkUrlDetail() throws SystemException {
		return housingIndexService.getLinkUrlDetail();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updateLinkDetail")
	@ResponseStatus( HttpStatus.OK )
	public void updateLinkDetail(@RequestBody String linkUrl) throws SystemException{
		housingIndexService.updateLinkDetail(linkUrl);
	}
	
}