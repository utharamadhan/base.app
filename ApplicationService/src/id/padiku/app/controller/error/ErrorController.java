package id.padiku.app.controller.error;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_ERROR)
public class ErrorController extends SuperController<Error> {

	@Autowired
	@Qualifier("errorService")
	private MaintenanceService<Error> maintenanceService;
	
	@Override
	public MaintenanceService<Error> getMaintenanceService() {
		return this.maintenanceService;
	}

	@Override
	public Error validate(Error anObject) throws SystemException {
		return null;
	}

}
