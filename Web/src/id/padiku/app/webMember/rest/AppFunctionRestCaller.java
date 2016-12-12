package id.padiku.app.webMember.rest;

import id.padiku.app.rest.QueryParamInterfaceRestCaller;
import id.padiku.app.rest.RestBase;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.rest.SpecificRestCaller;
import id.padiku.app.valueobject.AppFunction;
import id.padiku.app.valueobject.AppRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFunctionRestCaller extends RestBase<AppFunction>{
	protected static Logger LOGGER = LoggerFactory.getLogger(AppFunctionRestCaller.class);
	
	public AppFunctionRestCaller() {
		super();
	}
	
	public List<AppFunction> findAppFunctionByPermissionList(final List<AppRole> roles) {
		return new SpecificRestCaller(RestConstant.REST_SERVICE, RestServiceConstant.APP_FUNCTION_SERVICE).executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findAppFunctionByPermissionList";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roles", roles);
				return map;
			}
		});
	}
	
	public List<AppFunction> findAppFunctionMenuByUserRoles(final List<AppRole> roles) {
		return new SpecificRestCaller(RestConstant.REST_SERVICE, RestServiceConstant.APP_FUNCTION_SERVICE).executeGetList(new QueryParamInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findAppFunctionMenuByUserRoles";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roles", roles);
				return map;
			}
		});
	}
}
