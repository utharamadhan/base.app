package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.SystemParameter;
import id.base.app.mail.MailManager;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.course.Course;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;

@Scope(value="request")
@RequestMapping(value="/contact")
@Controller
public class ContactUsWebController extends BaseSiteController<Contact>{
	
	MailManager mailManager;
	
	 @Autowired
	 private MailSender mailSender;
	
	static Logger LOGGER = LoggerFactory.getLogger(ContactUsWebController.class);
	
	protected RestCaller<Contact> getRestCaller() {
		return new RestCaller<Contact>(RestConstant.REST_SERVICE, RestServiceConstant.CONTACT_SERVICE);
	}
	
	protected RestCaller<Lookup> getRestCallerLookup() {
		return new RestCaller<Lookup>(RestConstant.REST_SERVICE, RestServiceConstant.LOOKUP_SERVICE);
	}
	
	protected RestCaller<Course> getRestCallerCourse() {
		return new RestCaller<Course>(RestConstant.REST_SERVICE, RestServiceConstant.COURSE_SERVICE);
	}
	
	protected void setDefaultData(ModelMap model){
		setMenu(model);
		List<SearchFilter> filterCH = new ArrayList<SearchFilter>();
		List<SearchOrder> orderCH = new ArrayList<SearchOrder>();
		filterCH.add(new SearchFilter(Lookup.LOOKUP_GROUP_STRING, Operator.EQUALS, ILookupGroupConstant.CATEGORY_HELP));
		filterCH.add(new SearchFilter(Lookup.USAGE, Operator.LIKE, SystemConstant.LookupUsage.CONTACT));
		orderCH.add(new SearchOrder(Lookup.ORDER_NO_STRING, Sort.ASC));
		model.addAttribute("category", getRestCallerLookup().findAll(filterCH, orderCH));
		
		List<Course> courses = getRestCallerCourse().findAll(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
		model.addAttribute("courses", courses);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{type}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="type") String type){
		setDefaultData(model);
		
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		filter.add(new SearchFilter(Lookup.LOOKUP_GROUP_STRING, Operator.EQUALS, ILookupGroupConstant.CATEGORY_HELP));
		filter.add(new SearchFilter(Lookup.USAGE, Operator.LIKE, SystemConstant.LookupUsage.CONTACT));
		filter.add(new SearchFilter(Lookup.NAME, Operator.EQUALS, type));
		List<Lookup> typeCodeList = getRestCallerLookup().findAll(filter, order);
		if(!typeCodeList.isEmpty()){
			model.addAttribute("type", typeCodeList.get(0).getCode());
		}
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setDefaultData(model);
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/testEmail")
	public String testEmail(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setDefaultData(model);
		
		final String username = SystemParameter.EMAIL_USERNAME;
		final String password = SystemParameter.EMAIL_PASSWORD;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("bona.dvent@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear TESTING EMAIL,"
				+ "\n\n TESTTTTTTTTTTT, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			LOGGER.info(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sendEmail")
	@ResponseBody
	public Map<String, Object> sendEmail(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params){
		Map<String, Object> resultMap = new HashMap<>();
		final String username = SystemParameter.EMAIL_USERNAME;
		final String password = SystemParameter.EMAIL_PASSWORD;
		final String host = SystemParameter.EMAIL_HOST;
		final String port = SystemParameter.EMAIL_PORT;
		
		String contactName = params.get("name");
		String contactEmail = params.get("email");
		String contactMessage = params.get("message");
		String contactTelp = params.get("telp");
		String contactAddress = params.get("address");
		String contactInstansi = params.get("instansi");
		String captcha = params.get("j_captcha_response");
		String type = "".equals(params.get("type")) ? ILookupConstant.CategoryHelp.CALL_CENTER : params.get("type");
		String learning = null;
		String tema = null;
		
		if((contactName == null || "".equals(contactName)) || (contactEmail == null || "".equals(contactEmail)) || (contactMessage == null || "".equals(contactMessage)) || (captcha == null || "".equals(captcha))){
			resultMap.put("success", false);
	         resultMap.put("message", "Your email failed to processed, There was an empty field!");
	         return resultMap;
		}else{
			if(ILookupConstant.CategoryHelp.PROGRAM.equals(type)){
				learning = params.get("learning"); 
				if(learning == null){
					resultMap.put("success", false);
			         resultMap.put("message", "Your email failed to processed, There was an empty field!");
			         return resultMap;
				}
			}else if(ILookupConstant.CategoryHelp.CONSULT.equals(type)){
				tema = params.get("tema");
				if(tema==null){
					resultMap.put("success", false);
			         resultMap.put("message", "Your email failed to processed, There was an empty field!");
			         return resultMap;
				}
			}
		}
		
		Boolean isResponseCorrect =Boolean.FALSE;
		String trueKaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		if(captcha.equalsIgnoreCase(trueKaptcha)){
			isResponseCorrect = Boolean.TRUE;
		}
         
	     if(isResponseCorrect){
	    	 List<SearchFilter> filter = new ArrayList<SearchFilter>();
	 		List<SearchOrder> order = new ArrayList<SearchOrder>();
	 		filter.add(new SearchFilter(Lookup.CODE, Operator.EQUALS, type));
	 		List<Lookup> lookup = getRestCallerLookup().findAll(filter, order);
	 		
	 		Contact contact = Contact.getInstance();
	 		contact.setName(contactName);
	 		contact.setEmail(contactEmail);
	 		contact.setMessage(contactMessage);
	 		contact.setTelp(contactTelp);
	 		contact.setAddress(contactAddress);
	 		contact.setInstansi(contactInstansi);
	 		if(!lookup.isEmpty()){
	 			contact.setHelpLookup(lookup.get(0));
	 		}
	 		
	 		if(ILookupConstant.CategoryHelp.PROGRAM.equals(type)){
	 			if(learning != null){
	 				Course course = getRestCallerCourse().findById(new Long(learning));
	 				contact.setCourse(course);
	 			}
	 		}
	 		
	 		if(ILookupConstant.CategoryHelp.CONSULT.equals(type)){
	 			if(tema != null){
	 				Lookup contactTema = getRestCallerLookup().findById(new Long(tema));
	 				contact.setTemaLookup(contactTema);
	 			}
	 		}
	 		
	 		getRestCaller().saveOrUpdate(contact);
	 		
	 		
	 		String from = SystemParameter.EMAIL_SENDER;
	 		String subject = "Housing Finance Center - Contact Us";
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
	 	         
	 	         String text = "Dear "+contactEmail+",\n\nThank you for contacting us. We will contact you soon, Thank you\n\nBest Regards\n\nHousing Finance Center";
	 	        
	 	         message.setText(text);
	 	
	 	         
	 	         Transport.send(message);
	 	         
	 	         // Send to ourselves
	 	         Message messageSelf = new MimeMessage(session);
	 	         messageSelf.setFrom(new InternetAddress(from));
	 	         messageSelf.setRecipients(Message.RecipientType.TO,
	 	             InternetAddress.parse(from));
	 	         messageSelf.setSubject(subject);
	 	         
	 	         String textSelf = contactMessage;
	 	
	 	         messageSelf.setText(textSelf);
	 	
	 	         
	 	         Transport.send(messageSelf);
	 	         
	 	         
	 	         resultMap.put("success", true);
	 	         resultMap.put("message", "Thank you for contacting us. We will contact you soon.");
	 		}catch(MessagingException e) {
	 			resultMap.put("success", false);
	 	         resultMap.put("message", "Your email failed to processed");
	 	         return resultMap;
	 		}
	     }else{
	    	 resultMap.put("success", false);
 	         resultMap.put("message", "The captcha was not entered correctly, please try it again");
	     }
		
		return resultMap;
	}
	
}
