package id.base.app.webMember.controller.site;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.base.app.mail.MailManager;

@Scope(value="request")
@RequestMapping(value="/contact")
@Controller
public class ContactUsWebController {
	
	MailManager mailManager;
	
	 @Autowired
	 private MailSender mailSender;
	
	static Logger LOGGER = LoggerFactory.getLogger(ContactUsWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response){
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
		
		if(contactName == null || contactEmail == null || contactMessage == null){
			resultMap.put("success", false);
	         resultMap.put("message", "Your email failed to processed, There was an empty field!");
	         return resultMap;
		}
		
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
