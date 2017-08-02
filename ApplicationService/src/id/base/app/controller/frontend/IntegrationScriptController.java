package id.base.app.controller.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontend.IIntegrationScriptService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.frontend.IntegrationScript;
import id.base.app.valueobject.publication.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_INTEGRATION_SCRIPT)
public class IntegrationScriptController extends SuperController<IntegrationScript>{

	@Autowired
	private IIntegrationScriptService integrationScriptService;
	
	@Override
	public MaintenanceService<IntegrationScript> getMaintenanceService() {
		return integrationScriptService;
	}

	@Override
	public IntegrationScript validate(IntegrationScript anObject)
			throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getUrl())) {
			errorList.add(new ErrorHolder(IntegrationScript.URL, messageSource.getMessage("error.mandatory", new String[]{"URL"}, Locale.ENGLISH)));
		}
		return null;
	}
	
	@Override
	public IntegrationScript preUpdate(IntegrationScript anObject) throws SystemException{
		return validate(anObject);
	}
}
