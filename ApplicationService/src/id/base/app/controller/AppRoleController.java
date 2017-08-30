package id.base.app.controller;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.role.IRoleService;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppRole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(RestConstant.RM_ROLE)
public class AppRoleController extends SuperController<AppRole>{
	
	@Autowired
	@Qualifier("roleService")
	private MaintenanceService<AppRole> maintenanceService;
	
	@Autowired
	private IRoleService service;

	@RequestMapping(method=RequestMethod.GET, value="/findInternalRoles")
	@ResponseBody
	public List<AppRole> findInternalRoles() {
		return service.findInternalRoles();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllRoleCodeAndName")
	@ResponseBody
	public List<AppRole> findAllRoleCodeAndName() {
		return service.findAllRoleCodeAndName();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findExternalRoles")
	@ResponseBody
	public List<AppRole> findExternalRoles() {
		return service.findExternalRoles();
	}

	@RequestMapping(method=RequestMethod.GET, value="/listByIds")
	@ResponseBody
	public List<AppRole> findObjects(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		return maintenanceService.findObjects(objectPKs);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	@ResponseBody
	public List<AppRole> findAllRole() {
		return service.findAllRole();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listAllByFilter")
	@ResponseBody
	public List<AppRole> findAllByFilter(
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson, 
			@RequestParam(value="order", defaultValue="", required=false) String orderJson)
			throws SystemException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<SearchFilter> filter = mapper.readValue(filterJson, new TypeReference<List<SearchFilter>>(){});
			List<SearchOrder> order = mapper.readValue(orderJson, new TypeReference<List<SearchOrder>>(){});
			return service.findAllByFilter(filter, order);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByIdFetchUsers/{id}")
	@ResponseBody
	public AppRole findByIdFetchUsers(@PathVariable("id") Long pkAppRole) {
		return service.findByIdFetchUsers(pkAppRole);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAppRolesByAppUserId/{id}")
	@ResponseBody
	public List<AppRole> findAppRolesByAppUserId(@PathVariable("id") Long pkAppRole) {
		return service.findAppRolesByAppUserId(pkAppRole);
	}

	@RequestMapping(method=RequestMethod.POST, value="/saveNewUsers")
	@ResponseStatus( HttpStatus.OK )
	public void saveNewUsers(@RequestParam(value="userRolePK") Long userRolePK, @RequestParam(value="pkUsers") Long[] pkUsers) {
		service.saveNewUsers(userRolePK, pkUsers);
	}
	
	@Override
	public AppRole preCreate(AppRole anObject) throws SystemException {
			anObject.setType(SystemConstant.USER_TYPE_INTERNAL);
		return validate(anObject);
	}
	
	@Override
	public AppRole preUpdate(AppRole anObject) throws SystemException { 
			anObject.setType(SystemConstant.USER_TYPE_INTERNAL);
		return validate(anObject);
	}
	
	@Override
	public AppRole validate(AppRole anObject) throws SystemException {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		if(StringFunction.isEmpty(anObject.getCode())){
			errorHolders.add(new ErrorHolder(messageSource.getMessage("error.message.user.role.code.mandatory", null, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())){
			errorHolders.add(new ErrorHolder(messageSource.getMessage("error.message.user.role.name.mandatory", null, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getName());
			List<String> permalinkDBList = service.getSamePermalink(anObject.getPkAppRole(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(errorHolders.size()>0){
			throw new SystemException(errorHolders);
		}
		return anObject;
	}
	

	@Override
	public MaintenanceService<AppRole> getMaintenanceService() {
		return this.maintenanceService;
	}
}
