package id.base.app.web;

import id.base.app.JSONConstant;
import id.base.app.SystemConstant;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.GeneralFunctions;
import id.base.app.valueobject.AppFunction;
import id.base.app.valueobject.RuntimeUserLogin;
import id.base.app.web.rest.LoginRestCaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ShortLifeSessionFilter2 implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(ShortLifeSessionFilter2.class);
	
	private static final Set<String> BYPASS_TOKEN = new HashSet<String>();
	static{
		BYPASS_TOKEN.add("/do/landingPage");
		BYPASS_TOKEN.add("/do/landingPage/loginPost");
		BYPASS_TOKEN.add("/do/landingPage/blank");
		BYPASS_TOKEN.add("/do/token/tokenExpired");
		BYPASS_TOKEN.add("/do/token/tokenInvalid");
		BYPASS_TOKEN.add("/do/registration");
		BYPASS_TOKEN.add("/do/registration/submit");
		BYPASS_TOKEN.add("/do/login/loginFromActivation");
		BYPASS_TOKEN.add("/do/login/registerCompany");
		BYPASS_TOKEN.add("/do/registration/activationPage");
		BYPASS_TOKEN.add("/do/registration/activationSubmit");
	}
	
	private static final String URL_ACTIVATION = "/do/registration/activation";
	private static final String WEB_SOCKET_CONTROLLER = "webSocketController";
	private static final String FORGOT_PASSWORD = "/forgotPassword";
	private static final String LOGIN = "/do/login";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String requestURIminusCtxPath = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		LOGGER.info("SLSFilter2:"+requestURIminusCtxPath+" is being filtered");
		String redirect = null;
		if(BYPASS_TOKEN.contains(requestURIminusCtxPath) 
				|| requestURIminusCtxPath.startsWith(URL_ACTIVATION)
					|| requestURIminusCtxPath.contains(WEB_SOCKET_CONTROLLER)
						|| requestURIminusCtxPath.contains(FORGOT_PASSWORD)){
			chain.doFilter(request, response);
		} else {
			Cookie[] cookies = request.getCookies();
			String cookieValue = null;
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if(JSONConstant.KEY_TOKEN.equals(cookie.getName())){
						cookieValue = cookie.getValue();
						break;
					}
				}
			}
			boolean cookieValid = true;
			Map<String, String> tokenMap = new HashMap<>();
			if(cookieValue!=null){
				tokenMap = GeneralFunctions.getTokenMap(cookieValue);
				if(!validateToken(requestURIminusCtxPath, tokenMap)){
					cookieValid = false;
				}
			}
			
			if(cookieValid){
				RuntimeUserLogin login = new LoginRestCaller().findByUserName(tokenMap.get("username"));
				if(requestURIminusCtxPath.equals(LOGIN) && login != null) {
					WebGeneralFunction.deleteLogin(login);
					login = null;
				}
				if(login!=null){
					if(null != login.getAccessInfo()){
						try {
							List<AppFunction> function = WebGeneralFunction.findAppFunctionByAccessPage(request, requestURIminusCtxPath);
							if(!function.isEmpty()){
								boolean canAccess = false;
								Map<String, Object> accessInfos = new ObjectMapper().readValue(login.getAccessInfo(), new TypeReference<HashMap<String, Object>>(){});
								for (Map.Entry<String, ArrayList<String>> menu : ((LinkedHashMap<String, ArrayList<String>>) accessInfos.get("menus")).entrySet()) {
									ArrayList<String> data = menu.getValue();
									String accessMenu = data.get(0).toString();
									if(requestURIminusCtxPath.equals(accessMenu)){
										canAccess = true;
										break;
									}
								}
								if(!canAccess){
									redirect = request.getContextPath()+"/do/landingPage/blank";
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(redirect == null){
						try {
							if(SystemConstant.USER_TYPE_INTERNAL==login.getSessionType().intValue()){
								WebGeneralFunction.buildLoginSession(login,request);
								tokenMap.put(JSONConstant.KEY_LOGIN_SECURITY, login.getToken());
								response.addCookie(GeneralFunctions.buildCookie(request.getContextPath(), tokenMap));
							}else{
								try{
									WebGeneralFunction.createLogin(request, tokenMap, login.getToken());
									tokenMap.put(JSONConstant.KEY_LOGIN_SECURITY, login.getToken());
									response.addCookie(GeneralFunctions.buildCookie(request.getContextPath(), tokenMap));
								}catch(Exception e){
									redirect = request.getContextPath()+"/do/landingPage/blank";
								}
							}
						} catch (Exception e) {
							redirect = SystemConstant.LOGIN_URL;
						}
					}
				}else{
					try{
						String token = WebGeneralFunction.createLogin(request, tokenMap, null);
						tokenMap.put(JSONConstant.KEY_LOGIN_SECURITY, token);
						response.addCookie(GeneralFunctions.buildCookie(request.getContextPath(), tokenMap));
					}catch(Exception e){
						redirect = SystemConstant.LOGIN_URL + "?error=wrongAccount";
					}
				}
			}else{
				redirect = SystemConstant.LOGIN_URL;
			}
			if(redirect!=null){
				if(request.getHeader("X-Requested-With") != null){
					request.setAttribute("message","<b>Login Required:</b> Your session is either expired or you are not login.");
	                request.getRequestDispatcher( SystemConstant.LOGIN_URL+"?error=tokenExpired" ).forward(request, response);
				}else if(request.getParameter("posodaDrevo")!=null&&request.getParameter("posodaDrevo").equals("true")){
					request.setAttribute("message","<b>Login Required:</b> Your session is either expired or you are not login.");
	                request.getRequestDispatcher( SystemConstant.LOGIN_URL+"?error=tokenExpired" ).forward(request, response);
				}else{
					response.sendRedirect(redirect);
				}
			}else{
				chain.doFilter(request, response);
			}
			if(request.getSession(false) != null){
				request.getSession(false).removeAttribute(SessionConstants.USER_OBJECT_KEY);
			}
		}
	}
	
	private Boolean validateToken(String requestURI, final Map<String, String> tokenMap) {
		if(requestURI.equals(LOGIN)) {
			return Boolean.TRUE;	
		}
		try {
			return new SpecificRestCaller<Boolean>(RestConstant.REST_SERVICE, RestConstant.RM_AUTHENTICATION, Boolean.class).executeGet(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/isTokenValid/{userId}/{token}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("userId", tokenMap.get("username"));
						map.put("token", tokenMap.get(JSONConstant.KEY_LOGIN_SECURITY));
					return map;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
