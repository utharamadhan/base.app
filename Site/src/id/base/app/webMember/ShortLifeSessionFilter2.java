package id.base.app.webMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.aboutUs.CommonPost;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.aboutUs.ProgramPost;
import id.base.app.valueobject.publication.ResearchReport;
import id.base.app.valueobject.research.Research;

@Component
public class ShortLifeSessionFilter2 implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(ShortLifeSessionFilter2.class);
	
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
		//post
		RestCaller<CommonPost> restCall = new RestCaller<CommonPost>(RestConstant.REST_SERVICE, RestServiceConstant.COMMON_POST_SERVICE);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(CommonPost.PK_COMMON_POST, Sort.ASC));
		List<CommonPost> posts = restCall.findAll(filter, order); 
		request.setAttribute("posts", posts);
		
		//engagement
		RestCaller<Engagement> restCallEngage = new RestCaller<Engagement>(RestConstant.REST_SERVICE, RestServiceConstant.ENGAGEMENT_SERVICE);
		List<SearchFilter> filterEngage = new ArrayList<SearchFilter>();
		List<SearchOrder> orderEngage = new ArrayList<SearchOrder>();
		orderEngage.add(new SearchOrder(Engagement.PK_ENGAGEMENT, Sort.ASC));
		List<Engagement> engages = restCallEngage.findAll(filterEngage, orderEngage); 
		request.setAttribute("engages", engages);
		
		//program
		RestCaller<ProgramPost> restCallProgram = new RestCaller<ProgramPost>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_POST_SERVICE);
		List<SearchFilter> filterProgram = new ArrayList<SearchFilter>();
		List<SearchOrder> orderProgram = new ArrayList<SearchOrder>();
		orderProgram.add(new SearchOrder(ProgramPost.PK_PROGRAM_POST, Sort.ASC));
		List<ProgramPost> programs = restCallProgram.findAll(filterProgram, orderProgram); 
		request.setAttribute("programs", programs);
		
		//research
		RestCaller<ResearchReport> restCallResearch = new RestCaller<ResearchReport>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_REPORT_SERVICE);
		List<SearchFilter> filterResearch = new ArrayList<SearchFilter>();
		List<SearchOrder> orderResearch = new ArrayList<SearchOrder>();
		orderResearch.add(new SearchOrder(ResearchReport.PK_RESEARCH_REPORT, Sort.ASC));
		List<ResearchReport> researchs = restCallResearch.findAll(filterResearch, orderResearch); 
		request.setAttribute("researchs", researchs);
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
