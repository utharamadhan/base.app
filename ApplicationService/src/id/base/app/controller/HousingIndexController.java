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
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
}