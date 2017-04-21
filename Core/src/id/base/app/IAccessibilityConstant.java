package id.base.app;

import id.base.app.util.ReflectionFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dito
 * 
 */
public class IAccessibilityConstant {

	public static int INT_INTERNAL_FUNCTION  = 1;
	
	public static int INT_DASHBOARD = 100;
	
	public static int INT_ADMINISTRATOR 		= 200;
		public static int INT_APP_ROLE			= 210;
		public static int INT_APP_USER			= 220;
		public static int INT_BUSINESS_SETTING 	= 230;
		public static int INT_REFERENCE 		= 240;
		public static int INT_FRONT_END_MENU 	= 250;
	
	public static int INT_MASTER = 300;
		public static int INT_TESTIMONIAL = 310;
		public static int INT_FAQ = 320;
		
	public static int INT_FRONT_END_DISPLAY = 400;
		public static int INT_FED_MENU = 410;
		public static int INT_FED_SLIDESHOW = 420;
		public static int INT_FED_BACKGROUND_IMAGE = 430;
		public static int INT_FED_HOME_SETTING = 440;
		public static int INT_FED_FOOTER_LINK_URL = 450;

	public static int INT_ABOUT_US = 500;
		public static int INT_AU_COMMON_POST = 510;

	public static int INT_ACTIVITY = 600;
		public static int INT_ACT_ENGAGEMENT = 610;
		public static int INT_ACT_PROGRAM = 620;
		
	public static int INT_PUBLICATION = 700;
		public static int INT_PUB_EBOOK = 710;
		public static int INT_PUB_NEWS = 720;
		public static int INT_PUB_EVENT = 730;
		public static int INT_PUB_HOUSING_INDEX = 740;
		
	public static int INT_LEARNING = 800;
		public static int INT_LEARNING_PROGRAM = 810;
		public static int INT_LEARNING_COURSE_TAG = 820;
		public static int INT_LEARNING_COURSE = 830;
		
	public static int INT_RESEARCH = 900;
		public static int INT_RESEARCH_THEME = 910;
		public static int INT_RESEARCH_MAINTENANCE = 920;
		public static int INT_RESEARCH_MANAGEMENT = 930;

	public static int INT_ADVISORY = 1000;
		public static int INT_ADVISORY_SUB_MENU = 1010;
		public static int INT_ADVISORY_COMMON_POST = 1020;
		public static int INT_ADVISORY_ISSUE_INSIGHT = 1030;
		public static int INT_ADVISORY_CONSULTING = 1040;

	public static int INT_STUDENT_DATABASE = 1100;
		public static int INT_SD_MAINTENANCE = 1110;
		public static int INT_SD_UPDATE_LEARNING = 1120;
		
	public static int INT_CONTACT_US = 1200;
		public static int INT_CU_MAINTENANCE = 1210;
		public static int INT_CU_USER_CONTACT = 1220;
		
	public static int INT_REPORT = 1300;
		public static int INT_REPORT_RND = 1310;
		public static int INT_REPORT_STUDENT = 1320;
	
	public static List<Long> MENU_LIST = new ArrayList<>();
	static {
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_INTERNAL_FUNCTION));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_DASHBOARD));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ADMINISTRATOR));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_MASTER));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_FRONT_END_DISPLAY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ABOUT_US));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ACTIVITY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_PUBLICATION));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_LEARNING));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_RESEARCH));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ADVISORY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_STUDENT_DATABASE));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_CONTACT_US));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_REPORT));
	}
	
	public static void synchronizeFunction(String functionName, int functionNumber){
		if(functionName!=null){
			ReflectionFunction.setProperties(IAccessibilityConstant.class, functionName, String.valueOf(functionNumber));
		}
	}
}
