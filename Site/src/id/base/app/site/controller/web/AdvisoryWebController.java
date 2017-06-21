package id.base.app.site.controller.web;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.paging.PagingWrapper;
import id.base.app.properties.ApplicationProperties;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.advisory.AdvisoryConsulting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;

@Scope(value="request")
@RequestMapping(value="/advisory")
@Controller
public class AdvisoryWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(AdvisoryWebController.class);
	
	protected RestCaller<AdvisoryConsulting> getRestCaller() {
		return new RestCaller<AdvisoryConsulting>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<Category> getRestCallerCategory() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
	}
	
	protected RestCaller<AppUser> getRestCallerUser() {
		return new RestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE);
	}
	
	protected RestCaller<AdvisoryConsulting> getRestCallerAdvisory() {
		return new RestCaller<AdvisoryConsulting>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/advisory1")
	public String advisory1(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/advisory/advisory1";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/advisory2")
	public String advisory2(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/advisory/advisory2";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/advisory3")
	public String advisory3(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/advisory/advisory3";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/advisory4")
	public String advisory4(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "/advisory/advisory4";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		
		//Advisor
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppUser.APP_ROLES_CODE, Operator.EQUALS, SystemConstant.UserRole.ADVISOR));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(AppUser.PK_APP_USER, Sort.ASC));
		List<AppUser> advisors = new ArrayList<AppUser>();
		advisors = getRestCallerUser().findAll(filter, order);
		model.addAttribute("advisors", advisors);
		
		//Category
		List<Category> categories = new ArrayList<Category>();
		categories = getRestCallerCategory().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("categories", categories);
		return "/advisory/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<AdvisoryConsulting> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("advisories", advisories);
		return "/advisory/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{title}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		AdvisoryConsulting detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getName()!=null){
				String dataTitle = detail.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("detail", detail);
					return "/advisory/detail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<AdvisoryConsulting> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("advisories", advisories);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/stories")
	public String sub(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/stories";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/advisor")
	public String advisor(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppUser.APP_ROLES_CODE, Operator.EQUALS, SystemConstant.UserRole.ADVISOR));
		if(filterJson!=null && !"".equals(filterJson)){
			filter.add(new SearchFilter(AppUser.PARTY_NAME, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<AppUser> advisors = getRestCallerUser().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("advisors", advisors);
		
		//Category
		List<Category> categories = new ArrayList<Category>();
		categories = getRestCallerCategory().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("categories", categories);
		return "/advisory/advisor";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/advisor/load")
	@ResponseBody
	public Map<String, Object> advisorLoad(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppUser.APP_ROLES_CODE, Operator.EQUALS, SystemConstant.UserRole.ADVISOR));
		if(filterJson!=null && !"".equals(filterJson)){
			filter.add(new SearchFilter(AppUser.PARTY_NAME, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<AppUser> advisors = getRestCallerUser().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("advisors", advisors);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/advisor/detail/{id}/{name}")
	public String detailAdvisor(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long pkAppUser,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@PathVariable(value="name") String name
		){
		AppUser advisor = getRestCallerUser().findById(pkAppUser);
		if(advisor!=null){
			if(advisor.getParty()!=null && advisor.getParty().getName()!=null){
				String dataTitle = advisor.getParty().getName().replace(" ", "-");
				if(dataTitle.equals(name)){
					model.addAttribute("advisor", advisor);
					return "/advisory/advisorDetail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/consulting")
	public String consulting(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam Map<String,String> params
		){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppUser.APP_ROLES_CODE, Operator.EQUALS, SystemConstant.UserRole.ADVISOR));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(AppUser.PK_APP_USER, Sort.ASC));
		List<AppUser> advisors = new ArrayList<AppUser>();
		advisors = getRestCallerUser().findAll(filter, order);
		model.addAttribute("advisors", advisors);
		
		//Category
		List<Category> categories = new ArrayList<Category>();
		categories = getRestCallerCategory().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("categories", categories);
		
		return "/advisory/consulting";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/category/detail/{id}/{title}")
	public String detailCategory(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long pkCategory,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@PathVariable(value="title") String title
		){
		Category category = getRestCallerCategory().findById(pkCategory);
		if(category!=null){
			if(category.getTitle()!=null){
				String dataTitle = category.getTitle().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("category", category);
					
					//get question
					List<SearchFilter> filterAdvisory = new ArrayList<SearchFilter>();
					filterAdvisory.add(new SearchFilter(AdvisoryConsulting.CATEGORY_PK, Operator.EQUALS, pkCategory, Long.class));
					List<SearchOrder> orderAdvisory = new ArrayList<SearchOrder>();
					List<AdvisoryConsulting> advisories = getRestCallerAdvisory().findAll(filterAdvisory, orderAdvisory);
					model.addAttribute("advisories", advisories);
					return "/advisory/categoryDetail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/askQuesstion")
	@ResponseBody
	public Map<String, Object> sendEmail(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params){
		Map<String, Object> resultMap = new HashMap<>();
		final String username = ApplicationProperties.getProperty("email.smtp.username");
		final String password = ApplicationProperties.getProperty("email.smtp.password");
		final String host = ApplicationProperties.getProperty("mail.smtp.host");
		final String port = ApplicationProperties.getProperty("mail.smtp.port");
		
		String contactName = params.get("name");
		String contactEmail = params.get("email");
		String contactQuestion = params.get("question");
		String contactTelp = params.get("telp");
		String captcha = params.get("j_captcha_response");
		Long category = params.get("category")!=null && !"".equals(params.get("category")) ? new Long(params.get("category")) : 0L;
		Long advisor = params.get("advisor")!=null && !"".equals(params.get("advisor")) ? new Long(params.get("advisor")) : 0L;
		
		if(contactName == null || contactEmail == null || contactQuestion == null || "".equals(contactName) || "".equals(contactEmail) || "".equals(contactQuestion) || (captcha == null || "".equals(captcha))){
			resultMap.put("success", false);
	         resultMap.put("message", "Your question failed to processed, There was an empty field!");
	         return resultMap;
		}
		
		Boolean isResponseCorrect =Boolean.FALSE;
		String trueKaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		if(captcha.equalsIgnoreCase(trueKaptcha)){
			isResponseCorrect = Boolean.TRUE;
		}
         
         if(isResponseCorrect){
			//Save Advisory
			AdvisoryConsulting advisory = AdvisoryConsulting.getInstance();
			advisory.setName(contactName);
			advisory.setEmail(contactEmail);
			advisory.setTelp(contactTelp);
			advisory.setQuestion(contactQuestion);
			advisory.setStatus(SystemConstant.StatusAdvisory.NEW);
			if(category.compareTo(0L)>0){
				Category dataCategory = getRestCallerCategory().findById(category);
				advisory.setCategory(dataCategory);
			}
			if(advisor.compareTo(0L)>0){
				AppUser dataAppUser = getRestCallerUser().findById(advisor);
				advisory.setTutor(dataAppUser);
			}
			List<ErrorHolder> errors = getRestCaller().create(advisory);
			if(!errors.isEmpty()){
				resultMap.put("success", false);
		         resultMap.put("message", "Your question failed to processed, There was an empty field!");
		         return resultMap;
			}
			
			String from = "mardyemailaja@gmail.com";
			String subject = "Contact Email";
			StringBuffer to = new StringBuffer();
			to.append(contactEmail);
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.from", from);
	
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
		
			try{
				 Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		             InternetAddress.parse(to.toString()));
		         message.setSubject(subject);
		         
		         String text = "Thank you for contacting us. We will immediately contact you, Thank you!";
		
		         message.setText(text);
		
		         Transport.send(message);
		         
			}catch(MessagingException e) {
				resultMap.put("success", false);
	 	         resultMap.put("message", "Your email failed to processed");
			}
			resultMap.put("success", true);
	        resultMap.put("message", "Your question successfully been processed");
        }else{
	    	 resultMap.put("success", false);
 	         resultMap.put("message", "Your email failed to processed");
	     }
		return resultMap;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
}
