package id.base.app;

import id.base.app.util.ReflectionFunction;

/**
 * @author dito
 * 
 */
public class IAccessibilityConstant {

	public static int INT_INTERNAL_FUNCTION  = 1;
	
	public static int INT_DASHBOARD = 100;
	
	public static int INT_SEC_ADMIN = 200;
	public static int INT_SEC_ADMIN_USER = 210;
	public static int INT_SEC_ADMIN_USER_ROLE = 220;
	public static int INT_SEC_ADMIN_RMU = 230;
	
	public static int INT_SYS_ADMIN = 300;
	public static int INT_SYS_ADMIN_BUS_REFERENCE = 310;
	public static int INT_SYS_ADMIN_ERROR_LOG = 320;
	public static int INT_SYS_ADMIN_SYS_PARAMETER = 330;
	public static int INT_SYS_ADMIN_EMAIL_MAINTENANCE = 340;
	public static int INT_SYS_FAQ = 350;
	public static int INT_SYS_HELPER = 360;
	
	public static int INT_FRONTEND = 400;
	public static int INT_FRONTEND_SLIDESHOW = 410;
	public static int INT_FRONTEND_TEAM = 420;
	public static int INT_FRONTEND_ABOUT = 430;
	public static int INT_FRONTEND_CONTACT = 440;
	
	public static int INT_REPORT = 500;
	
	public static int EX_EXTERNAL_FUNCTION = 2;
	
	public static int EX_DASHBOARD = 1100;
	public static int EX_DASHBOARD_INFO_USER = 1110;
	public static int EX_DASHBOARD_LAST_USER_ACTIVITY = 1120;
	public static int EX_DASHBOARD_TOTAL_STOCK_IN_HAND = 1130;
	public static int EX_DASHBOARD_RES_PROD_PIE_CHART = 1140;
	public static int EX_DASHBOARD_RES_PROD_TABLE = 1150;
	public static int EX_DASHBOARD_ALL_RMU = 1160;
	public static int EX_DASHBOARD_ALL_STOCK_BERAS_GRAFIK = 1170;
	public static int EX_DASHBOARD_ALL_10_RMU_BEST = 1180;
	public static int EX_DASHBOARD_ALL_10_RMU_WORST = 1190;
	public static int EX_DASHBOARD_ALL_PRICE = 1200;
	
	public static int EX_BUY = 1400;
	
	public static int EX_PROCESS = 1500;
	
	public static int EX_SELL = 1600;
	
	public static int EX_STOCK = 1700;
	public static int EX_STOCK_PROD = 1710;
	public static int EX_STOCK_NON_PROD = 1720;
	
	public static int EX_MITRA = 1800;
	
	public static int EX_SUPER_REPORT = 1900;
	
	public static int EX_REPORT = 2000;
	
	public static int EX_SETTING = 2100;
	public static int EX_SETTING_PARAMETER = 2110;
	public static int EX_SETTING_REFERENCE = 2120;
	
	public static void synchronizeFunction(String functionName, int functionNumber){
		if(functionName!=null){
			ReflectionFunction.setProperties(IAccessibilityConstant.class, functionName, String.valueOf(functionNumber));
		}
	}
}
