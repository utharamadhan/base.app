package id.base.app.rest;

public class RestConstant {
	public static final String USER_CALLER = "uLUoI0BMv1";
	public static String REST_SERVICE = "http://localhost:8181/ApplicationService";
	public static String WEB_ADMIN_SERVICE = "http://localhost:8080/Web/do";
	
	public static void setRestService(String url){
		REST_SERVICE = url;
	}
	
	public static void setWebAdminService(String url) {
		WEB_ADMIN_SERVICE = url;
	}
	
	public static final String RM_WEB_SOCKET = "/webSocketController";
	public static final String RM_NOTIFICATION = "/notification";
	public static final String RM_FORGOT_PASSWORD = "/forgotPassword";
	
	public static final String RM_DASHBOARD = "/dashboard";
	
	public static final String RM_USER = "/user";
	public static final String RM_ROLE = "/role";
	
	public static final String RM_LOOKUP = "/lookup";
	public static final String RM_LOOKUP_GROUP = "/lookupGroup";
	public static final String RM_LOOKUP_ADDRESS = "/lookupAddress";
	public static final String RM_LOOKUP_ADDRESS_GROUP = "/lookupAddressGroup";
	public static final String RM_MASTER_ADDRESS = "/masterAddress";
	public static final String RM_MASTER_FEE = "/masterFee";
	public static final String RM_ERROR = "/error";
	public static final String RM_SYSTEM_PARAMETER = "/systemParameter";
	public static final String RM_EMAIL_TEMPLATE = "/emailTemplate";
	public static final String RM_FAQ = "/faq";
	public static final String RM_HELPER = "/helper";
	
	public static final String RM_SLIDESHOW = "/slideshow";
	public static final String RM_TEAM = "/team";
	public static final String RM_ABOUT = "/about";
	public static final String RM_CONTACT = "/contact";
	
	public static final String RM_REPORT = "/report";
	
	public static final String RM_USER_LOGIN = "/userLogin";
	public static final String RM_AUTHENTICATION = "/auth";
	public static final String RM_MAIL = "/mailController";
	
	public static final String RM_ROLE_FUNCTION = "/roleFunction";
	public static final String RM_APP_FUNCTION = "/appFunction";
	public static final String RM_AUDIT = "/audit";
	public static final String RM_SECURITYPARAM = "/securityParam";
	
	public static final String RM_PARTY = "/party";
	public static final String RM_PARTY_ROLE = "/partyRole";
	public static final String RM_PARTY_ADDRESS = "/partyAddress";
	public static final String RM_PARTY_ID = "/partyID";
	public static final String RM_PARTY_CONTACT = "/partyContact";
	public static final String RM_STUDENT = "/student";
	
	//initial wizard master
	public static final String RM_INITIAL_WIZARD = "/initialWizard";
	
	//about us
	public static final String RM_COMMON_POST = "/aboutUs/commonPost";
	public static final String RM_ENGAGEMENT = "/aboutUs/engagement";
	public static final String RM_PROGRAM_POST = "/aboutUs/programPost";
	public static final String RM_TUTOR = "/aboutUs/tutor";
	
	//publication
	public static final String RM_RESEARCH_REPORT = "/publication/researchReport";
	public static final String RM_DIGITAL_BOOK = "/publication/digitalBook";
	
	//news
	public static final String RM_NEWS = "/news/newsMaintenance";
	
	//event
	public static final String RM_EVENT = "/event/eventMaintenance";
	
	//learning
	public static final String RM_GROUP_COURSE = "/course/groupCourse";
	public static final String RM_COURSE = "/course/course";
	public static final String RM_COURSE_TAG = "/course/courseTag";
	
	//research
	public static final String RM_RESEARCH_THEME = "/research/researchTheme";
	public static final String RM_RESEARCH = "/research/research";
	
	//advisory
	public static final String RM_ADVISORY = "/advisory";
	public static final String RM_ADVISORY_MENU = "/advisory/menu";
	public static final String RM_ADVISORY_POST = "/advisory/post";
	public static final String RM_ADVISOR = "/advisory/advisor";
	public static final String RM_ADVISORY_CATEGORY = "/advisory/category";
	public static final String RM_ADVISORY_ARTICLE = "/advisory/article";

	//testimonial
	public static final String RM_TESTIMONIAL = "/testimonial";
	
	//link URL
	public static final String RM_LINK_URL = "/linkUrl";
	
	public static final String RM_HOUSING_INDEX = "/housingIndex";
	
	//report
	public static final String RM_STUDENT_REPORT = "/studentReport";
	public static final String RM_RESEARCH_DEVELOPMENT_REPORT = "/researchDevelopmentReport";
	
	//Front End
	public static final String RM_HOME_SETTING = "/homeSetting";
	public static final String RM_CONTACT_SETTING = "/contactSetting";

}
