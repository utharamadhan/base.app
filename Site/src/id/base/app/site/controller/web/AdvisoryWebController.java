package id.base.app.site.controller.web;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.properties.ApplicationProperties;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.advisory.AdvisoryConsulting;
import id.base.app.valueobject.program.ProgramItem;
import id.base.app.valueobject.program.ProgramItemMenu;

import java.util.ArrayList;
import java.util.Collections;
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
@RequestMapping(value="/main-program/advisory")
@Controller
public class AdvisoryWebController extends BaseSiteController<Pages>{
	
	static Logger LOGGER = LoggerFactory.getLogger(AdvisoryWebController.class);
	
	protected RestCaller<AdvisoryConsulting> getRestCaller() {
		return new RestCaller<AdvisoryConsulting>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<Category> getRestCallerCategory() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "redirect:/page/main-program/advisory/"+getFirstPermalinkData();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String main(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		setCommonData(request,model);
		model.addAttribute("permalink", permalink);
		List<Category> categoryList = getCategoryList();
		model.addAttribute("categories", categoryList);
		Boolean foundPermalink = false;
		for (Category category : categoryList) {
			if(permalink.equalsIgnoreCase(category.getPermalink())){
				model.addAttribute("category", category);
				Boolean hasItemPermalink = false; 
				if(category.getItems()!=null && category.getItems().size()>0 && category.getItems().get(0).getPermalink()!=null){	
					model.addAttribute("itemPermalink", category.getItems().get(0).getPermalink());
					hasItemPermalink = true;
				}
				if(category.getIsItemsNewPage()!=null && category.getIsItemsNewPage()){
					if(hasItemPermalink){
						return "forward:/page/main-program/advisory/"+permalink+"/"+category.getItems().get(0).getPermalink();
					}else{
						return "forward:/page/main-program/advisory/consulting";		
					}
				}
				try {
					model.addAttribute("encodedDetailLinkImageURL", category.getEncodedDetailLinkImageURL());
				} catch (Exception e) {
					e.printStackTrace();
				}
				foundPermalink = true;
				break;
			}
		}
		if(foundPermalink){
			return "/advisory/main";
		}else{
			LOGGER.error("ERROR DATA NOT FOUND");
			return "redirect:/page/notfound";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{title}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		AdvisoryConsulting detail = getRestCaller().findById(id);
		if(detail!=null){
			if(detail.getName()!=null){
				String dataTitle = detail.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					setCommonData(request,model);
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
		setCommonData(request,model);
		return "/advisory/stories";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{categoryPermalink}/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="categoryPermalink") String categoryPermalink,
			@PathVariable(value="permalink") String permalink){
		ProgramItem detail = findItemByPermalink(permalink);
		if(detail!=null){
			setCommonData(request,model);
			List<ProgramItemMenu> menus = detail.getMenus();
			Collections.sort(menus, new ProgramItemMenu());
			model.addAttribute("menus", menus);
			model.addAttribute("categoryPermalink", categoryPermalink);
			model.addAttribute("detail", detail);
			return "/advisory/itemDetail";
		}
		LOGGER.error("ERROR DATA NOT FOUND");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/consulting")
	public String consulting(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam Map<String,String> params
		){
		setCommonData(request,model);
		
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
					setCommonData(request,model);
					model.addAttribute("category", category);
					
					//get question
					List<SearchFilter> filterAdvisory = new ArrayList<SearchFilter>();
					filterAdvisory.add(new SearchFilter(AdvisoryConsulting.CATEGORY_PK, Operator.EQUALS, pkCategory, Long.class));
					List<SearchOrder> orderAdvisory = new ArrayList<SearchOrder>();
					List<AdvisoryConsulting> advisories = getRestCaller().findAll(filterAdvisory, orderAdvisory);
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
	
	private String getFirstPermalinkData() throws SystemException {
		return new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_CATEGORY, String.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getFirstPermalinkData/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.ADVISORY);
				return map;
			}
		});
	}
	
	private List<Category> getCategoryList(){
		SpecificRestCaller<Category> rc = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
		List<Category> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleDataWithItemsForList/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.ADVISORY);
				return map;
			}
		});
		return list;
	}
	
	private ProgramItem findItemByPermalink(final String permalink){
		ProgramItem detail = new ProgramItem();
		try{
			detail = new SpecificRestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestConstant.RM_PROGRAM_ITEM, ProgramItem.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/findByPermalink/{permalink}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("permalink", permalink);
					return map;
				}
			});
			
		}catch(Exception e){
			detail = null;
		}
		return detail;
	}
	
}
