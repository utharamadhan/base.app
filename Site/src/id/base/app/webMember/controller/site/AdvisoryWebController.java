package id.base.app.webMember.controller.site;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.base.app.ILookupConstant;
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
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.advisory.Advisory;
import id.base.app.valueobject.advisory.AdvisoryMenu;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.course.Course;

@Scope(value="request")
@RequestMapping(value="/advisory")
@Controller
public class AdvisoryWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(AdvisoryWebController.class);
	
	protected RestCaller<Advisory> getRestCaller() {
		return new RestCaller<Advisory>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<AdvisoryMenu> getRestCallerMenu() {
		return new RestCaller<AdvisoryMenu>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_MENU_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		List<AdvisoryMenu> menus = getRestCallerMenu().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("menus", menus);
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
		PagingWrapper<Advisory> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("advisories", advisories);
		return "/advisory/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id){
		Advisory detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/advisory/detail";
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
		PagingWrapper<Advisory> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
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
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/advisor";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/advisor/detail")
	public String detailAdvisor(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/advisorDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article")
	public String article(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/article";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article/detail")
	public String detailArticle(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/articleDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/consulting")
	public String consulting(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/consulting";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/consulting/detail")
	public String detailConsulting(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		return "/advisory/consultingDetail";
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
		
		if(contactName == null || contactEmail == null || contactQuestion == null || "".equals(contactName) || "".equals(contactEmail) || "".equals(contactQuestion)){
			resultMap.put("success", false);
	         resultMap.put("message", "Your question failed to processed, There was an empty field!");
	         return resultMap;
		}
		
		//Save Advisory
		Advisory advisory = Advisory.getInstance();
		advisory.setName(contactName);
		advisory.setEmail(contactEmail);
		advisory.setTelp(contactTelp);
		advisory.setQuestion(contactQuestion);
		advisory.setStatus(SystemConstant.StatusAdvisory.NEW);
		List<ErrorHolder> errors = getRestCaller().create(advisory);
		if(!errors.isEmpty()){
			resultMap.put("success", false);
	         resultMap.put("message", "Your question failed to processed, There was an empty field!");
	         return resultMap;
		}
		
		String from = "mardy@infoflow.co.id";
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
			e.printStackTrace();
		}
		resultMap.put("success", true);
        resultMap.put("message", "Your question successfully been processed");
		return resultMap;
	}
	
}
