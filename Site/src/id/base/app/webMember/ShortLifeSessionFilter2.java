package id.base.app.webMember;

import id.base.app.SystemConstant;
import id.base.app.valueobject.RuntimeUserLogin;
import id.base.app.webMember.rest.LoginRestCaller;

import java.io.IOException;
import java.util.HashSet;
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
		BYPASS_TOKEN.add("/do/login/setCompanySelected");
		BYPASS_TOKEN.add("/do/registration/activationPage");
		BYPASS_TOKEN.add("/do/registration/activationSubmit");
		BYPASS_TOKEN.add("/do/aboutUs/commonPost/showList");
	}
	
	private static final String URL_ACTIVATION = "/do/registration/activation";
	private static final String URL_INITIAL_WIZARD = "initialWizard";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
