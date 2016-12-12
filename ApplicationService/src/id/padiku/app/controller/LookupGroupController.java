package id.padiku.app.controller;

import java.util.List;

import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.ILookupGroupService;
import id.padiku.app.valueobject.LookupGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_LOOKUP_GROUP)
public class LookupGroupController extends SuperController<LookupGroup>{

	@Autowired
	@Qualifier("lookupGroupService")
	private MaintenanceService<LookupGroup> maintenanceService;
	
	@Autowired
	private ILookupGroupService lookupGroupService;
	
	@Override
	public MaintenanceService<LookupGroup> getMaintenanceService() {
		return this.maintenanceService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLookupGroupByName/{name}")
	public LookupGroup findLookupGroupByName(@PathVariable( "name" ) String name) {
		return lookupGroupService.findByName(name);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/findLookupGroupForCompany")
	@ResponseBody
	public List<LookupGroup> findLookupGroupForCompany() {
		return lookupGroupService.findLookupGroupForCompany();
	}

	@Override
	public LookupGroup validate(LookupGroup anObject) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
