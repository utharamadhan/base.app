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
		public static int INT_DS_CONTACT_US = 110;
		public static int INT_DS_RND = 120;
	
	public static int INT_ADMINISTRATOR 	= 200;
		public static int INT_ADM_APP_ROLE	= 210;
		public static int INT_ADM_APP_USER	= 220;
		public static int INT_ADM_BUS_SET 	= 230;
		public static int INT_ADM_REF 		= 240;		
	
	public static int INT_MASTER = 300;
		public static int INT_MT_PROGRAM = 310;
		public static int INT_MT_TESTIMONIAL = 320;
		public static int INT_MT_FAQ = 330;
		
	public static int INT_FRONT_END_DISPLAY = 400;
		public static int INT_FE_MENU_SET = 410;
		public static int INT_FE_HOME_SET = 420;
		public static int INT_FE_PAGE = 430;
		public static int INT_FE_FOOTER_LINK_URL = 430;

	public static int INT_ABOUT_US = 500;
		public static int INT_AU_COMMON_POST = 510;
		public static int INT_AU_ENGAGEMENT = 520;
		public static int INT_AU_PROGRAM = 530;
		
	public static int INT_PUBLICATION = 600;
		public static int INT_PB_EBOOK = 610;
		public static int INT_PB_NEWS = 620;
		public static int INT_PB_EVENT = 630;
		public static int INT_PB_HOUSING_IDX = 640;
		
	public static int INT_LEARNING = 700;
		public static int INT_LG_CATEGORY = 710;
		public static int INT_LG_ITEM = 720;
		
	public static int INT_RESEARCH = 800;
		public static int INT_RD_CATEGORY = 810;
		public static int INT_RD_ITEM = 820;
		public static int INT_RD_MANAGEMENT = 830;

	public static int INT_ADVISORY = 900;
		public static int INT_ADV_CATEGORY = 910;
		public static int INT_ADV_CONTACT = 920;
		public static int INT_ADV_CONSULTING = 930;

	public static int INT_STUDENT_DATABASE = 1000;
		public static int INT_SD_MAINTENANCE = 1010;
		public static int INT_SD_UPDATE_LEARNING = 1020;
		
	public static int INT_CONTACT_US = 1100;
		public static int INT_CU_SCREEN = 1110;
		public static int INT_CU_USER_CONTACT = 1120;
		
	public static int INT_REPORT = 1200;
		public static int INT_RT_RND = 1210;
		public static int INT_RT_STUDENT = 1220;
	
	public static List<Long> MENU_LIST = new ArrayList<>();
	static {
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_INTERNAL_FUNCTION));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_DASHBOARD));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ADMINISTRATOR));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_MASTER));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_FRONT_END_DISPLAY));
		MENU_LIST.add(Long.valueOf(IAccessibilityConstant.INT_ABOUT_US));
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
