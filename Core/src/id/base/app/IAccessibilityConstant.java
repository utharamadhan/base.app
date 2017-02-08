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
		
	public static int INT_FRONT_END_DISPLAY = 300;
		public static int INT_FED_MENU = 310;
		public static int INT_FED_SLIDESHOW = 320;
		public static int INT_FED_BACKGROUND_IMAGE = 330;
		public static int INT_FED_HOME_SETTING = 340;

	public static int INT_ABOUT_US = 400;
		public static int INT_AU_COMMON_POST = 410;

	public static int INT_ACTIVITY = 500;
		public static int INT_ACT_ENGAGEMENT = 510;
		public static int INT_ACT_PROGRAM = 520;
		
	public static int INT_PUBLICATION = 600;
		public static int INT_PUB_EBOOK = 610;
		public static int INT_PUB_NEWS = 620;
		public static int INT_PUB_EVENT = 630;
		
	public static int INT_LEARNING = 700;
		public static int INT_LEARNING_PROGRAM = 710;
		public static int INT_LEARNING_COURSE_TAG = 720;
		public static int INT_LEARNING_COURSE = 730;
		
	public static int INT_RESEARCH = 800;
		public static int INT_RESEARCH_THEME = 810;
		public static int INT_RESEARCH_MAINTENANCE = 820;
		public static int INT_RESEARCH_MANAGEMENT = 830;

	public static int INT_ADVISORY = 900;
		public static int INT_ADVISORY_SUB_MENU = 910;
		public static int INT_ADVISORY_COMMON_POST = 920;
		public static int INT_ADVISORY_ISSUE_INSIGHT = 930;
		public static int INT_ADVISORY_CONSULTING = 940;

	public static int INT_STUDENT_DATABASE = 1000;
		public static int INT_SD_MAINTENANCE = 1010;
		public static int INT_SD_UPDATE_LEARNING = 1020;
		
	public static int INT_CONTACT_US = 1100;
		public static int INT_CU_MAINTENANCE = 1110;
		public static int INT_CU_USER_CONTACT = 1120;

	public static List<Long> MENU_LIST = new ArrayList<>();
	static {
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_INTERNAL_FUNCTION));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_DASHBOARD));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ADMINISTRATOR));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_FRONT_END_DISPLAY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ABOUT_US));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ACTIVITY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_PUBLICATION));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_LEARNING));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_RESEARCH));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ADVISORY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_STUDENT_DATABASE));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_CONTACT_US));
	}
	
	public static void synchronizeFunction(String functionName, int functionNumber){
		if(functionName!=null){
			ReflectionFunction.setProperties(IAccessibilityConstant.class, functionName, String.valueOf(functionNumber));
		}
	}
}
