package id.base.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.function.IAppFunctionService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppFunction;
import id.base.app.valueobject.AppRole;

@RestController
@RequestMapping(RestConstant.RM_APP_FUNCTION)
public class AppFunctionController {
	@Autowired
	IAppFunctionService appFunctionService;
	
	@RequestMapping(value="/findAppFunctionByPermissionList")
	@ResponseBody
	public List<AppFunction> findAppFunctionByPermissionList(@RequestParam(value="roles") String roles) throws SystemException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<AppRole> appRoles = new ArrayList<AppRole>();
			if(StringUtils.isNotEmpty(roles)){
				appRoles = mapper.readValue(roles, new TypeReference<List<AppRole>>(){});
			}
			return appFunctionService.findAppFunctionByPermissionList(appRoles);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}

	@RequestMapping(value="/findAppFunctionMenuByUserRoles")
	@ResponseBody
	public List<AppFunction> findAppFunctionMenuByUserRoles(@RequestParam(value="roles") String roles) throws SystemException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<AppRole> appRoles = new ArrayList<AppRole>();
			if(StringUtils.isNotEmpty(roles)){
				appRoles = mapper.readValue(roles, new TypeReference<List<AppRole>>(){});
			}
			return appFunctionService.findAppFunctionMenuByUserRoles(appRoles);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(value="/findAppFunctionByAppRole")
	@ResponseBody
	public List<AppFunction> findAppFunctionByAppRole(@RequestParam(value="pkAppRole") Long pkAppRole) throws Exception {
		return appFunctionService.findAppFunctionByAppRole(pkAppRole);
	}
	
	@RequestMapping(value="/findAppFunctionByAccessPage")
	@ResponseBody
	public List<AppFunction> findAppFunctionByAccessPage(@RequestParam(value="accessPage") String accessPage) throws SystemException {
		try {
			if(StringUtils.isNotEmpty(accessPage)){
				return appFunctionService.findAppFunctionByAccessPage(accessPage);
			}
			return new ArrayList<AppFunction>();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(value="/findAllAppFunction")
	@ResponseBody
	public List<AppFunction> findAll() throws SystemException {
		try {
			List<AppFunction> list = appFunctionService.findAllAppFunction();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
}
