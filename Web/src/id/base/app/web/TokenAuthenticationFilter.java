package id.base.app.web;

import id.base.app.JSONConstant;
import id.base.app.util.GeneralFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationFilter implements Filter{
	
	private static String LANDING_PAGE = "";
	private static String REGISTRATION = "/do/registration";
	private static String ACTIVATION_PAGE = "/do/registration/activationPage";
	private static String ACTIVATION = "/do/registration/activation/";
	private static String LOGIN_FROM_ACTIVATION = "/do/login/loginFromActivation";
	private static String ACTIVATION_SUBMIT = "/do/registration/activationSubmit";
	private static String REGISTRATION_SUBMIT = REGISTRATION + "/submit";
	private static String TOKEN_EXPIRED = "";
	private static String TOKEN_EXPIRED_AJAX = "";
	private static String REGEN_EXPIRE = "15";
	private static String WEB_SOCKET = "/webSocketController";
	private static String FORGOT_PASSWORD = "/forgotPassword";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String urlTokenExpired = TOKEN_EXPIRED;
		String urlTokenExpiredAjax = request.getContextPath()+TOKEN_EXPIRED_AJAX;
		String urlRegistration = request.getContextPath()+REGISTRATION;
		String urlActivation = request.getContextPath()+ACTIVATION;
		String urlActivationLogin = request.getContextPath()+LOGIN_FROM_ACTIVATION;
		String urlActivationPage = request.getContextPath()+ACTIVATION_PAGE;
		String urlActivationSubmit = request.getContextPath()+ACTIVATION_SUBMIT;
		String urlRegistrationSubmit = request.getContextPath()+REGISTRATION_SUBMIT;
		String urlTokenExpiredWithoutDec = urlTokenExpiredAjax.substring(0,urlTokenExpired.indexOf("?")-1);
		String urlLandingPage = request.getContextPath()+LANDING_PAGE;
		if(request.getRequestURI().equals(urlTokenExpiredWithoutDec)){
			chain.doFilter(request, response);
		}else{		
			Cookie cookie = null;
			boolean doChain = false;
			if(request.getRequestURI().equals(urlLandingPage) || request.getRequestURI().equals(urlActivationLogin)){
				String token = request.getParameter(JSONConstant.KEY_TOKEN);
				if(token!=null){
					cookie = new Cookie(JSONConstant.KEY_TOKEN, token);
					cookie.setMaxAge(GeneralFunctions.getExpiryFromToken(token));
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					response.sendRedirect(urlLandingPage);
				}else{
					doChain = true;
				}
			} else if(request.getRequestURI().equals(urlRegistration) 
					|| request.getRequestURI().equals(urlRegistrationSubmit) 
						|| request.getRequestURI().startsWith(urlActivation)
							|| request.getRequestURI().equals(urlActivationPage) 
								|| request.getRequestURI().equals(urlActivationSubmit)
									|| request.getRequestURI().contains(WEB_SOCKET)
										|| request.getRequestURI().contains(FORGOT_PASSWORD)) {
				chain.doFilter(request, response);
			} else {
				doChain = true;
			}
			
			if(doChain){
				Cookie cookieValue = getCookie(request.getCookies(), request);
				if(cookieValue!=null){	
					Cookie newCookie = cookieValue;
					newCookie.setValue(cookieValue.getValue());
					newCookie.setMaxAge((60*Integer.valueOf(REGEN_EXPIRE)));
					newCookie.setPath(request.getContextPath());
					response.addCookie(newCookie);
					chain.doFilter(request, response);
				}else{
					cookie = new Cookie(JSONConstant.KEY_TOKEN, "");
					cookie.setMaxAge(0);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
					if(header != null && header.equals("XMLHttpRequest")) {
						response.sendRedirect(urlTokenExpiredAjax);
					} else {
						response.sendRedirect(urlTokenExpired);
					}
				}
			}
		}
	}
	
	private Cookie getCookie(Cookie[] cookies, HttpServletRequest request) {
		Cookie cookie = null;
		if(cookies!=null){
			for (Cookie cook : cookies) {
				if(cook.getName().equals(JSONConstant.KEY_TOKEN)){
					cookie = new Cookie(JSONConstant.KEY_TOKEN, cook.getValue().replace("|", "%"));
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(-1);
					break;
				}
			}
		}
		return cookie;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String propertyLocation = arg0.getInitParameter("filterConfigFile");
		File file = new File(propertyLocation);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			Properties p = new Properties();
			p.load(fis);
			LANDING_PAGE = p.getProperty("landingPage");
			TOKEN_EXPIRED = p.getProperty("tokenExpired");
			TOKEN_EXPIRED_AJAX = p.getProperty("tokenExpiredAjax");
			REGEN_EXPIRE = p.getProperty("regenExpire");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}