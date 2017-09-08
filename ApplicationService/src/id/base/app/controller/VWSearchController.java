package id.base.app.controller;

import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.search.IVWSearchService;
import id.base.app.valueobject.VWSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_VW_SEARCH)
public class VWSearchController extends SuperController<VWSearch>{

	@Autowired
	private IVWSearchService vwSearchService;
	
	@Override
	public MaintenanceService<VWSearch> getMaintenanceService() {
		return vwSearchService;
	}

	@Override
	public VWSearch validate(VWSearch anObject) throws SystemException {
		return null;
	}
	
}