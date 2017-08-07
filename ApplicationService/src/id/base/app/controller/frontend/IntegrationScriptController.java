package id.base.app.controller.frontend;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontend.IIntegrationScriptService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.frontend.IntegrationScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		if(!errorList.isEmpty()) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public IntegrationScript preUpdate(IntegrationScript anObject) throws SystemException{
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/findByUrl")
	@ResponseBody
	public Map<String, Object> findByUrl(@RequestParam Map<String, Object> mapUrl) throws SystemException {
		List<IntegrationScript> isList = integrationScriptService.findByUrl(String.valueOf(mapUrl.get("url")));
		Map<String, Object> resultMap = new HashMap<>();
		for (IntegrationScript is : isList) {
			if(SystemConstant.IntegrationScriptPosition.HEADER.equals(is.getPosition())){
				resultMap.put("header", is.getScript());	
			}else{
				resultMap.put("footer", is.getScript());
			}	
		}
		return resultMap;
	}
}