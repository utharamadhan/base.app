package id.base.app.site.controller.web;

import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.SystemParameter;
import id.base.app.exception.ErrorHolder;
import id.base.app.mail.MailManager;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.site.rest.LookupRestCaller;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.program.ProgramItem;

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
	
	protected RestCaller<ProgramItem> getRestCallerCourse() {
		return new RestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_ITEM_SERVICE);
	}
	
	protected void setDefaultData(HttpServletRequest request, ModelMap model, String categoryPermalink){
		setCommonData(request,model);
		LookupRestCaller lrc = new LookupRestCaller();
		List<SearchFilter> filterCH = new ArrayList<SearchFilter>();
		List<SearchOrder> orderCH = new ArrayList<SearchOrder>();
		filterCH.add(new SearchFilter(Lookup.LOOKUP_GROUP_STRING, Operator.EQUALS, ILookupGroupConstant.CATEGORY_HELP));
		filterCH.add(new SearchFilter(Lookup.USAGE, Operator.LIKE, SystemConstant.LookupUsage.CONTACT));
		orderCH.add(new SearchOrder(Lookup.ORDER_NO_STRING, Sort.ASC));
		model.addAttribute("categoryHelps", getRestCallerLookup().findAll(filterCH, orderCH));
		List<Category> categories = getCategoryList();
		model.addAttribute("categories", categories);
		if(categoryPermalink==null){
			if(!categories.isEmpty()){
				model.addAttribute("programItems", getProgramItemList(categories.get(0).getPkCategory()));
			}
		}else{
			for (Category category : categories) {
				if(categoryPermalink.equalsIgnoreCase(category.getPermalink())){
					model.addAttribute("titleCategorySelected", category.getTitle());		
				}
			}
			model.addAttribute("programItems", getProgramItemListByCategoryPermalink(categoryPermalink));
		}
		model.addAttribute("professionOptions", lrc.findByLookupGroup(ILookupGroupConstant.PROFESSION));
		model.addAttribute("informationFromOptions", lrc.findByLookupGroup(ILookupGroupConstant.INFORMATION_FROM));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setDefaultData(request, model, null);
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{categoryPermalink}/{permalink}")
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response
			, @PathVariable(value="categoryPermalink") String categoryPermalink, @PathVariable(value="permalink") String permalink){
		setDefaultData(request, model, categoryPermalink);
		model.addAttribute("categoryPermalink", categoryPermalink);
		model.addAttribute("permalink", permalink);
		return "/contact/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getProgramItemsByCategory")
	@ResponseBody
	public List<ProgramItem> getProgramItemsByCategory(@RequestParam("pkCategory") final Long pkCategory){
		return getProgramItemList(pkCategory);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/testEmail")
	public String testEmail(ModelMap model, HttpServletRequest request, HttpServletResponse response){
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
	
	@RequestMapping(method=RequestMethod.POST, value="/submit")
	@ResponseBody
	public Map<String, Object> submit(final Contact anObject, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> params){
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			errors = new SpecificRestCaller<Contact>(RestConstant.REST_SERVICE, RestServiceConstant.CONTACT_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
			else{
				resultMap.put("success", true);
				resultMap.put("message", "Terima kasih telah mendaftarkan diri dalam program kami. Kami akan segera mengubungi anda.");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	private List<Category> getCategoryList(){
		SpecificRestCaller<Category> rc = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
		List<Category> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleDataForSelect/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.ALL);
				return map;
			}
		});
		return list;
	}
	
	private List<ProgramItem> getProgramItemList(final Long pkCategory){
		SpecificRestCaller<ProgramItem> rc = new SpecificRestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_ITEM_SERVICE);
		List<ProgramItem> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findForSelectEligibleReg/{pkCategory}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("pkCategory", pkCategory);
				return map;
			}
		});
		return list;
	}
	
	private List<ProgramItem> getProgramItemListByCategoryPermalink(final String categoryPermalink){
		SpecificRestCaller<ProgramItem> rc = new SpecificRestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_ITEM_SERVICE);
		List<ProgramItem> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findForSelectEligibleRegByCategoryPermalink/{categoryPermalink}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("categoryPermalink", categoryPermalink);
				return map;
			}
		});
		return list;
	}
	
}
