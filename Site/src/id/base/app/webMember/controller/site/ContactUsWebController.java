package id.base.app.webMember.controller.site;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.mail.MailManager;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@RequestMapping(value="/contact")
@Controller
public class ContactUsWebController {
	
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
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params){
		if(params!=null && params.get("type")!=null){
			String type = params.get("type");
			model.addAttribute("type", type);
			if(ILookupConstant.CategoryHelp.PROGRAM.equals(type)){
				List<SearchFilter> filter = new ArrayList<SearchFilter>();
				List<SearchOrder> order = new ArrayList<SearchOrder>();
				List<Course> courses = getRestCallerCourse().findAll(filter, order);
				model.addAttribute("courses", courses);
			}
			
			if(ILookupConstant.CategoryHelp.CONSULT.equals(type)){
				List<SearchFilter> filter = new ArrayList<SearchFilter>();
				List<SearchOrder> order = new ArrayList<SearchOrder>();
				filter.add(new SearchFilter(Lookup.LOOKUP_GROUP_STRING, Operator.EQUALS, ILookupGroupConstant.CONTACT_TEMA));
				List<Lookup> temas = getRestCallerLookup().findAll(filter, order);
				model.addAttribute("temas", temas);
			}
		}
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sendEmail")
	@ResponseBody
	public Map<String, Object> sendEmail(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params){
		Map<String, Object> resultMap = new HashMap<>();
		final String username = "mardyemailaja@gmail.com";
		final String password = "anakilang";
		
		String contactName = params.get("name");
		String contactEmail = params.get("email");
		String contactMessage = params.get("message");
		String contactTelp = params.get("telp");
		String contactAddress = params.get("address");
		String contactInstansi = params.get("instansi");
		String type = params.get("type");
		String learning = null;
		String tema = null;
		
		if(contactName == null || contactEmail == null || contactMessage == null){
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
		contact.setHelpLookup(lookup.get(0));
		
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
		
		
		String from = "mardy@infoflow.co.id";
		String subject = "Contact Email";
		StringBuffer to = new StringBuffer();
		to.append(contactEmail);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
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
	         resultMap.put("message", "Your email successfully been processed");
		}catch(MessagingException e) {
			resultMap.put("success", false);
	         resultMap.put("message", "Your email failed to processed");
	         return resultMap;
		}
		
		return resultMap;
	}
	
}
