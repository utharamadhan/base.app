package id.base.app.site;

import id.base.app.JSONConstant;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.valueobject.course.Tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShortLifeSessionFilter2 implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(ShortLifeSessionFilter2.class);
	
	protected RestCaller<Tag> getRestTagCaller() {
		return new RestCaller<Tag>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_TAG_SERVICE);
	}
	
	private static final Set<String> BYPASS_TOKEN = new HashSet<String>();
	static{
		BYPASS_TOKEN.add("/page/landingPage");
		BYPASS_TOKEN.add("/page/landingPage/loginPost");
		BYPASS_TOKEN.add("/page/landingPage/blank");
		BYPASS_TOKEN.add("/page/token/tokenExpired");
		BYPASS_TOKEN.add("/page/token/tokenInvalid");
		BYPASS_TOKEN.add("/page/registration");
		BYPASS_TOKEN.add("/page/registration/submit");
		BYPASS_TOKEN.add("/page/login/loginFromActivation");
		BYPASS_TOKEN.add("/page/login/registerCompany");
		BYPASS_TOKEN.add("/page/login/setCompanySelected");
		BYPASS_TOKEN.add("/page/registration/activationPage");
		BYPASS_TOKEN.add("/page/registration/activationSubmit");
		BYPASS_TOKEN.add("/page/aboutUs/commonPost/showList");
	}
	
	private static final String URL_ACTIVATION = "/page/registration/activation";
	private static final String URL_INITIAL_WIZARD = "initialWizard";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Cookie cookieValue = getCookie(request.getCookies(), request);
		if(cookieValue!=null){	
			chain.doFilter(request, response);
		}else{
			String url = request.getContextPath().replace("Site", "Auth");
			response.sendRedirect(url);
			return;
		}
	}
	
	private Cookie getCookie(Cookie[] cookies, HttpServletRequest request) {
		Cookie cookie = null;
		if(cookies!=null){
			for (Cookie cook : cookies) {
				if(cook.getName().equals("AUTH_KEY")){
					cookie = cook;
					break;
				}
			}
		}
		return cookie;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
