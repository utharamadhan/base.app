package id.base.app.controller.frontend;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.frontend.IIntegrationScriptService;
import id.base.app.valueobject.frontend.IntegrationScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		List<IntegrationScript> globalList = integrationScriptService.findGlobalScript();
		Map<String, Object> resultMap = new HashMap<>();
		String header = "";
		String footer = "";
		for (IntegrationScript is : isList) {
			if(SystemConstant.IntegrationScriptPosition.HEADER.equals(is.getPosition())){
				header = is.getScript();	
			}else{
				footer = is.getScript();
			}	
		}
		for (IntegrationScript is : globalList) {
			if(SystemConstant.IntegrationScriptPosition.HEADER.equals(is.getPosition())){
				header += " "+ is.getScript();
			}else{
				footer += " "+ is.getScript();
			}	
		}
		resultMap.put("header", header);
		resultMap.put("footer", footer);
		return resultMap;
	}
}