package id.base.app.site.controller.web;

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
import id.base.app.valueobject.advisory.Advisory;
import id.base.app.valueobject.advisory.AdvisoryMenu;
import id.base.app.valueobject.advisory.AdvisoryPost;
import id.base.app.valueobject.advisory.Article;
import id.base.app.valueobject.advisory.Category;

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
	
	protected RestCaller<Category> getRestCallerCategory() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_CATEGORY_SERVICE);
	}
	
	protected RestCaller<AppUser> getRestCallerUser() {
		return new RestCaller<AppUser>(RestConstant.REST_SERVICE, RestServiceConstant.USER_SERVICE);
	}
	
	protected RestCaller<Article> getRestCallerArticle() {
		return new RestCaller<Article>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_ARTICLE_SERVICE);
	}
	
	protected RestCaller<Advisory> getRestCallerAdvisory() {
		return new RestCaller<Advisory>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_SERVICE);
	}
	
	protected RestCaller<AdvisoryPost> getRestCallerPost() {
		return new RestCaller<AdvisoryPost>(RestConstant.REST_SERVICE, RestServiceConstant.ADVISORY_POST_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		//Advisor Menu 
		List<SearchOrder> orderMenu = new ArrayList<SearchOrder>();
		orderMenu.add(new SearchOrder(AdvisoryMenu.ORDER_NO, Sort.ASC));
		List<AdvisoryMenu> menus = getRestCallerMenu().findAll(new ArrayList<SearchFilter>(), orderMenu);
		model.addAttribute("menus", menus);
		double leftMenu = 0;
		double rightMenu = 0;
		double sizeMenu = menus.size();
		if(sizeMenu>0){
			leftMenu = Math.ceil(sizeMenu/2);
			rightMenu = sizeMenu - leftMenu;
		}
		model.addAttribute("leftMenu", leftMenu);
		model.addAttribute("rightMenu", rightMenu);
		
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
		PagingWrapper<Advisory> advisories = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("advisories", advisories);
		return "/advisory/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}/{title}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id,
			@PathVariable(value="title") String title){
		Advisory detail = getRestCaller().findById(id);
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
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/post/{id}/{title}")
	public String post(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long pkAdvisoryPost,
			@PathVariable(value="title") String title
		){
		AdvisoryPost detail = getRestCallerPost().findById(pkAdvisoryPost);
		if(detail!=null){
			if(detail.getName()!=null){
				String dataTitle = detail.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("detail", detail);
					return "/advisory/post";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
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
					List<SearchFilter> filter = new ArrayList<SearchFilter>();
					filter.add(new SearchFilter(Article.ADVISOR_PK, Operator.EQUALS, pkAppUser, Long.class));
					List<SearchOrder> order = new ArrayList<SearchOrder>();
					List<Article> articles = getRestCallerArticle().findAll(filter, order);
					model.addAttribute("articles", articles);
					return "/advisory/advisorDetail";
				}
			}
		}
		LOGGER.error("ERROR DATA NOT VALID");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article")
	public String article(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
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
		
		List<SearchFilter> filterArticle = new ArrayList<SearchFilter>();
		List<SearchOrder> orderArticle = new ArrayList<SearchOrder>();
		if(filterJson!=null && !"".equals(filterJson)){
			filterArticle.add(new SearchFilter(Article.NAME, Operator.LIKE, filterJson));
		}
		mapperFromParam(params, model, filterArticle);
		PagingWrapper<Article> articles = getRestCallerArticle().findAllByFilter(startNo, offset, filterArticle, orderArticle);
		model.addAttribute("articles", articles);
		return "/advisory/article";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article/load")
	@ResponseBody
	public Map<String, Object> articleLoad(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@RequestParam Map<String,String> params
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filterArticle = new ArrayList<SearchFilter>();
		List<SearchOrder> orderArticle = new ArrayList<SearchOrder>();
		if(filterJson!=null && !"".equals(filterJson)){
			filterArticle.add(new SearchFilter(Article.NAME, Operator.LIKE, filterJson));
		}
		mapperFromParam(params, model, filterArticle);
		PagingWrapper<Article> articles = getRestCallerArticle().findAllByFilter(startNo, offset, filterArticle, orderArticle);
		resultMap.put("articles", articles);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sub/article/detail/{id}/{title}")
	public String detailArticle(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long pkArticle,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson,
			@PathVariable(value="title") String title
		){
		Article article = new Article();
		article = getRestCallerArticle().findById(pkArticle);
		if(article!=null){
			if(article.getName()!=null){
				String dataTitle = article.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("article", article);
					List<SearchFilter> filter = new ArrayList<SearchFilter>();
					filter.add(new SearchFilter(AppUser.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
					filter.add(new SearchFilter(AppUser.ARTICLE_PK, Operator.EQUALS, pkArticle, Long.class));
					List<SearchOrder> order = new ArrayList<SearchOrder>();
					model.addAttribute("createdAdvisors", getRestCallerUser().findAll(filter, order));
					
					//get question
					List<SearchFilter> filterAdvisory = new ArrayList<SearchFilter>();
					filterAdvisory.add(new SearchFilter(Advisory.ARTICLE_PK, Operator.EQUALS, pkArticle, Long.class));
					if(article!=null && article.getCategory()!=null && article.getCategory().getPkCategory()!=null){
						Long pkCategory = article.getCategory().getPkCategory();
						filterAdvisory.add(new SearchFilter(Advisory.CATEGORY_PK, Operator.EQUALS, pkCategory, Long.class));
					}
					List<SearchOrder> orderAdvisory = new ArrayList<SearchOrder>();
					List<Advisory> advisories = getRestCallerAdvisory().findAll(filterAdvisory, orderAdvisory);
					model.addAttribute("advisories", advisories);
					return "/advisory/articleDetail";
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
		
		//Article 
		List<SearchFilter> filterArticle = new ArrayList<SearchFilter>();
		List<SearchOrder> orderArticle = new ArrayList<SearchOrder>();
		List<Article> articles = getRestCallerArticle().findAll(filterArticle, orderArticle);
		model.addAttribute("articles", articles);
		
		mapperFromParam(params, model, null);
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
			if(category.getName()!=null){
				String dataTitle = category.getName().replace(" ", "-");
				if(dataTitle.equals(title)){
					model.addAttribute("category", category);
					
					//get question
					List<SearchFilter> filterAdvisory = new ArrayList<SearchFilter>();
					filterAdvisory.add(new SearchFilter(Advisory.CATEGORY_PK, Operator.EQUALS, pkCategory, Long.class));
					List<SearchOrder> orderAdvisory = new ArrayList<SearchOrder>();
					List<Advisory> advisories = getRestCallerAdvisory().findAll(filterAdvisory, orderAdvisory);
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
		Long category = params.get("category")!=null && !"".equals(params.get("category")) ? new Long(params.get("category")) : 0L;
		Long article = params.get("research")!=null && !"".equals(params.get("research")) ? new Long(params.get("research")) : 0L;
		Long advisor = params.get("advisor")!=null && !"".equals(params.get("advisor")) ? new Long(params.get("advisor")) : 0L;
		
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
		if(category.compareTo(0L)>0){
			Category dataCategory = getRestCallerCategory().findById(category);
			advisory.setCategory(dataCategory);
		}
		if(article.compareTo(0L)>0){
			Article dataArticle = getRestCallerArticle().findById(article);
			advisory.setArticle(dataArticle);
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
	
	private void mapperFromParam(Map<String,String> params, ModelMap model, List<SearchFilter> filter){
		if(params.containsKey("chooseCategory") && params.get("chooseCategory")!=null){
			Long data = new Long(params.get("chooseCategory").toString());
			if(filter!=null){
				filter.add(new SearchFilter(Article.CATEGORY_PK, Operator.EQUALS, data, Long.class));
			}
			model.addAttribute("chooseCategory",data);
		}
		
		if(params.containsKey("chooseAdvisor") && params.get("chooseAdvisor")!=null){
			Long data = new Long(params.get("chooseAdvisor").toString());
			if(filter!=null){
				filter.add(new SearchFilter(Article.ADVISOR_PK, Operator.EQUALS, data, Long.class));
			}
			model.addAttribute("chooseAdvisor",data);
		}
		
		if(params.containsKey("chooseArticle") && params.get("chooseArticle")!=null){
			Long data = new Long(params.get("chooseArticle").toString());
			if(filter!=null){
				filter.add(new SearchFilter(Article.PK_ARTICLE, Operator.EQUALS, data, Long.class));
			}
			model.addAttribute("chooseArticle",data);
		}
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		LOGGER.error("ERROR PATH VARIABLE NOT VALID");
		return "redirect:/page/notfound";
	}
	
}
