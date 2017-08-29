package id.base.app.controller.program;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.program.IVWProgramItemService;
import id.base.app.valueobject.program.VWProgramItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_VW_PROGRAM_ITEM)
public class VWProgramItemController extends SuperController<VWProgramItem> {

	@Autowired
	private IVWProgramItemService vwProgramItemService;
	
	@Override
	public MaintenanceService<VWProgramItem> getMaintenanceService() {
		return vwProgramItemService;
	}

	@Override
	public VWProgramItem validate(VWProgramItem anObject)
			throws SystemException {
		return null;
	}
	
}