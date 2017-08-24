package id.base.app;

import id.base.app.util.FileManager;

import java.io.File;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Application Constant
 */
public class SystemConstant  {
	static Logger logger = LoggerFactory.getLogger(SystemConstant.class);
	
	public static final String SHARE_SESSION_INFO = "share-session-info";
	
	public static final String SALT = "...-baseapp-...";
	
	public static final String GLOBAL_PATH = "/project/baseApp/";
	
	public static final String B64_PREFIX = "data:image/%s;base64,";
	
	/***  Field Definition Constant***/
	public final static String FIELD_DEFINITION_REFRENCE = "Reference";

    /***  Date time format ***/
    public static final String SYSTEM_DATE_DAY = "dd";
    public static final String SYSTEM_DATE_MONTH = "MM";
    public static final String SYSTEM_DATE_MONTH_NAME = "MMM";
    public static final String SYSTEM_DATE_YEAR = "yyyy";
    public static final String SYSTEM_DATE_YEAR_SHORT = "yy";
    public static final String SYSTEM_DATE_MASK = "dd-MM-yyyy";
    public static final String SYSTEM_DATE_MONTH_DETAIL = "dd-MMM-yyyy";
    public static final String SYSTEM_DATE_MONTH_POINT = "dd.MMM.yyyy";
    public static final String SYSTEM_DATE_TIME_MASK="dd-MMM-yyyy HH:mm";
    public static final String SYSTEM_DATE_TIME_SECOND_MASK="dd-MMM-yyyy HH:mm:ss";
    public static final String SYSTEM_MONTH_YEAR_MASK = "MM-yyyy";
    public static final String SYSTEM_YEAR_MONTH_MASK = "yyyy-MM";
    public static final String SYSTEM_FULL_MONTH_YEAR_MASK = "MMMMM yyyy";
    public static final String SYSTEM_FULL_MONTH_FULL_DATE_MASK = "dd MMMMM yyyy";
    public static final String SYSTEM_TIME_MASK = "dd-MM-yyyy HH:mm:ss";
    public static final String SYSTEM_FULL_TIME_MASK = "dd MMMMM yyyy HH:mm:ss";
    public static final String SYSTEM_TIME_MASK_SECONDHAND = "dd-MM-yyyy HH:mm";
    public static final String HOUR_MINUTE_MASK = "HH:mm";
    public static final String HOUR_MINUTE_SECOND_MASK = "HH:mm:ss";
    public static final String HOUR_MINUTE_SECOND_MASK_NO_DELIMITER = "HHmmss";
    public static final String HOUR_MINUTE_MILISECOND_MASK_NO_DELIMITER = "HHmmssSSS";
    public static final String SYSTEM_DATE_MASK_NO_DELIMITER = "yyyyMMdd";
    public static final String SYSTEM_DATE_MASK_YEAR_MONTH = "yyyyMM";
    public static final String HOUR_MINUTE_MASK_NO_DELIMITER = "HHmm";
    public static final String DATABASE_DATE_FORMAT_STD = "MM/dd/yyyy";
    public static final String HOUR_MINUTE_SECOND_AMPM= "hh:mm:ss a";
    public static final String SYSTEM_FULL_DATE = "dd MM yyyy";
    public static final String SYSTEM_REPORT_DATE = "yyyyMMdd_HHmm";
    public static final String SYSTEM_REPORT_DATE_TIME = "yyyyMMdd_HHmmssSSS";
    public static final String SYSTEM_REPORT_MONTH="MMMMMMMMMM";
    public static final String SYSTEM_DATE_HOUR_MINUTE_NO_DELIMITER = "yyyyMMddHHmm";
    public static final String SYSTEM_DATE_TIME_NO_DELIMITER = "yyyyMMddhhmmss";
    
    public static final String WEB_SERVICE_DATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String WEB_SERVICE_DATE = "yyyy-MM-dd";
    public static final String WEB_SERVICE_TIME = "HH:mm:ss.SSS";
    public static final String SYSTEM_DATE_MASK_2 = "dd/MM/yyyy";
    
    public static final String SYSTEM_DATE_TIME_FORMAT = "EEE MMM d HH:mm:ss Z yyyy";
    
    public static final String EXPORT_DATETIME = "yyyy-MM-dd.HH-mm-ss-SSS";
    
    /*** Currency format ***/
    public static final String SYSTEM_CURRENCY_MASK = "#,##0.00";
    public static final String DECIMAL_MASK = "###0.00";
    public static final String SYSTEM_NUMBER_MASK = "#,##0";

   public static final String CLOSE_ON_HOLIDAY="TIDAK";
   public static final String OPEN_ON_HOLIDAY="YA";

    public static final Integer FIELD_TYPE_INT = 0;
    public static final Integer FIELD_TYPE_LONG = 1;
    public static final Integer FIELD_TYPE_DOUBLE = 2;
    public static final Integer FIELD_TYPE_STRING = 3;
    public static final Integer FIELD_TYPE_DATE = 4;
    public static final Integer FIELD_TYPE_BOOLEAN = 5;
    public static final Integer FIELD_TYPE_CRON = 6;

    /*** Data type for profile builder and FCS ***/
    public static final int DATATYPE_NUMBER = 0;
    public static final int DATATYPE_STRING = 1;
    public static final int DATATYPE_DATE = 2;
    public static final int DATATYPE_BOOLEAN = 3;
    
    public static final int BIGDECIMAL_SCALE = 4;
    public static final RoundingMode BIGDECIMAL_ROUNDING = RoundingMode.CEILING;

    //Values to support boolean value in database
    public static final int iTRUE       = 1;
    public static final int iFALSE      = 0;

    public static final String _TRUE    = String.valueOf(SystemConstant.iTRUE);
    public static final String _FALSE    = String.valueOf(SystemConstant.iFALSE);

    /*** List / table view sorting constant ***/
    public static final int SORT_ASCENDING = 0;
    public static final int SORT_DESCENDING = 1;

    public static final String AJAX_SUCCESS = "true";  
    public static final String AJAX_FAILED = "false";  
    public static final String AJAX_NOT_APPROVAL="notApproval";
    
    public static final String METHOD_NAME_ADD="add";
	public static final String METHOD_NAME_EDIT="edit";
	public static final String METHOD_NAME_SHOW="show";
	
	public static final int USER_TYPE_INTERNAL=1;
	public static final int USER_TYPE_EXTERNAL=2;
	public static final String USER_TYPE_INTERNAL_STR = "Internal";
	public static final String USER_TYPE_EXTERNAL_STR = "External";
    public static final String[] STR_USER_TYPE={"","Internal","Eksternal"};
    public static Map<String, String> userTypeMaps = new LinkedHashMap<>();
	static{
		userTypeMaps.put(String.valueOf(USER_TYPE_INTERNAL), USER_TYPE_INTERNAL_STR);
		//userTypeMaps.put(String.valueOf(USER_TYPE_EXTERNAL), USER_TYPE_EXTERNAL_STR);
	}
	
	public static final String RECAPCHA_PUBLIC_KEY ="6LdFYsESAAAAADc5B5e9brgINvhCEuYwt5QceKPK";
	public static final String RECAPCHA_PRIVATE_KEY="6LdFYsESAAAAAHSS-lJDGrgcGrvzdSfaLs-iUSZZ";
	public static final String RECAPCHA_CHALLENGE_FIELD="recaptcha_challenge_field";
	public static final String RECAPCHA_RESPONSE_FIELD="recaptcha_response_field";
	
	
	public static final String USER_TYPES="userType";
    
    public static final String DEFAULT_PASSWORD="master";
    
    public static final int HIBERNATE_JDBC_BATCH_SIZE = 20;
    
    public static final int SEX_TYPE_MAN=1;
    public static final int SEX_TYPE_WOMAN=2;
    
    public static final String [] JENIS_KELAMIN={"","Laki-laki","Perempuan"};
    
    public static final String AJAX_INDICATOR = "isAJAX";

    public static final String PATH_SEPARATOR="/";
    public static final String SEPARATOR_FILE_NAME = "__";
	
	public static final String MAIL_SEPERATOR = ";";

	public static final String SYSTEM_USER = "SYSTEM";
	
	public static final String _YES="YES";
	public static final String _NO="NO";

	public static final String EMAIL_DELIMITER = ";";
	public static final String REPORT_DELIMETER_NEW_LINE="_#N";
	public static final String PERCENTAGE_HTML_NUMBER="&#37;";
	public static final String PERCENTAGE_STRING="%";
	
	public static final String ERROR_LIST	= "errorList";
	public static final String REDIRECT = "loginPage";
	
	public static final String VIOLATION_EXCEPTION_MESSAGES = "could not execute batch";
	
	public static final String DEFAULT_USER_PASSWORD = "master";
	
	public static final String DEFAULT_REPORT_TEMPLATE_LOCATION = "template";
	
	public static final class ActivationMethod {
		public static final Integer EMAIL = 1;
		public static final Integer SHORT_MESSAGE_SERVICE = 2;
	}
	
	public static final class PROTOCOL{
		public static final int LOCAL 	= 0;
		public static final int FTP 	= 1;
		public static final int SFTP 	= 2;
		
		public static final int FTP_DEF_PORT = 21;
		public static final int SFTP_DEF_PORT = 21;
	}
	
	public static final List<String> arithmeticOperations = new LinkedList<String>();
	static{
		arithmeticOperations.add("+");
		arithmeticOperations.add("-");
		arithmeticOperations.add("*");
		arithmeticOperations.add("/");
	}
	
	
	public static String ACTIVATION_URL = "/Web/do/registration/activationPage";
	
	public static String UPDATE_PASSWORD_URL = "/Web/do/forgotPassword/updatePassword?token=";
	public static void setUpdatePasswordURL(String updatePasswordURL) {
		UPDATE_PASSWORD_URL = updatePasswordURL;
	}
	
	public static String LOGIN_URL = "http://hfc.com";
	public static void setUrl(String url){
		LOGIN_URL = url;
	}
	
	public static String ADMIN_URL = "http://hfc.com/WebAdmin";
	public static void setGoToUrl(String adminUrl){
		ADMIN_URL = adminUrl;
	}
	
	public static String WEB_TRANS_COOKIE_NAME = "token";
	public static String WEB_ADMIN_COOKIE_NAME = "token";
	public static String COOKIE_SEPARATOR = "%";
	public static String TOKEN_SEPARATOR = "|";
	
	public static String UNIQUE_TOKEN = "sSs";
	
	public static String SHARED_FOLDER_LOCATION 		= "/baseapp";
	public static String BILLING_REPORT_DIR 			= "D:/baseapp/billing/";
	public static String AUDIT_TRAIL_EXPORT_DIR 		= "D:/baseapp/auditTrail/";
	public static String FILE_DIRECTORY_TEMP 			= "D:/baseapp";
    public static String RESULT_DIRECTORY				= "/Result";
    public static String REMOTE_DIRECTORY 				= "/uploadFile/";
    public static String LOCAL_TEMP_DIR_EXPORT 			= FILE_DIRECTORY_TEMP + "/tempExport/";
    public static String LOCAL_TEMP_DIRECTORY_REPORT	= FILE_DIRECTORY_TEMP + "/report/";
    public static String LOCAL_TEMP_DIRECTORY_USER		= FILE_DIRECTORY_TEMP + "/user/";
    public static String FILE_STORAGE					= "";
    public static String FILE_CONTENT_DIRECTORY			= "contentDirectory" + File.separator;
    public static String FILE_FEATURED_IMAGE_DIRECTORY	= "featuredImage" + File.separator;
    public static String FILE_PHOTO_DIRECTORY			= "photo" + File.separator;
    public static String FILE_EBOOK_DIRECTORY			= "ebook" + File.separator;
	
	public static void setSharedFolderLocation(String sharedFolderLoc) throws Exception {
		SHARED_FOLDER_LOCATION = sharedFolderLoc;
		File file = new File(sharedFolderLoc);
		// if shared folder not exists/mounted and not writable, quit!!!
		if (!file.exists() || !file.canWrite()) {
			logger.error("Shared folder are not configured yet, can't start app");
			throw new Exception("Shared folder are not configured yet");
		}
		BILLING_REPORT_DIR = sharedFolderLoc + "/billing/";
		FileManager.createDir(BILLING_REPORT_DIR);
		
		AUDIT_TRAIL_EXPORT_DIR = sharedFolderLoc + "/auditTrail/";
		FileManager.createDir(AUDIT_TRAIL_EXPORT_DIR);
		
		FILE_DIRECTORY_TEMP = sharedFolderLoc + "/temp";
		FileManager.createDir(FILE_DIRECTORY_TEMP);
		
		RESULT_DIRECTORY = sharedFolderLoc + "/result/";
		FileManager.createDir(RESULT_DIRECTORY);
		
		REMOTE_DIRECTORY = sharedFolderLoc + "/uploadFile/";
		FileManager.createDir(REMOTE_DIRECTORY);
		
		LOCAL_TEMP_DIR_EXPORT = FILE_DIRECTORY_TEMP + "/export/";
		FileManager.createDir(LOCAL_TEMP_DIR_EXPORT);
		
		LOCAL_TEMP_DIRECTORY_REPORT = FILE_DIRECTORY_TEMP + "/report/";
		FileManager.createDir(LOCAL_TEMP_DIRECTORY_REPORT);
		
		LOCAL_TEMP_DIRECTORY_USER = FILE_DIRECTORY_TEMP + "/user/";
		FileManager.createDir(LOCAL_TEMP_DIRECTORY_USER);
		
		FILE_STORAGE = sharedFolderLoc + File.separator + "fileStorage" + File.separator;
		FileManager.createDir(FILE_STORAGE);
		
	}
	
	public static final class ValidFlag {
		public static Integer INVALID 			= 0;
		public static Integer VALID 			= 1;
		
		public static String INVALID_STR	= "Invalid";
		public static String VALID_STR		= "Valid";
		
		public static HashMap<Integer, String> validFlagMap = new HashMap<>();
		static{
			validFlagMap.put(INVALID, INVALID_STR);
			validFlagMap.put(VALID, VALID_STR);
		}
	}
	
	public static String IMAGE_SHARING_URL = "http://";
	
	public static void setImageSharingURL(String imageSharingURL) {
		IMAGE_SHARING_URL = imageSharingURL;
	}
	
	public static final String USER_OBJECT_KEY = "user";
	
	public static final String NAME = "name";
	public static final String CODE = "code";
	
	public static final class CurrencyDataType {
		public static int PRECISION = 18;
		public static int SCALE = 4;
	}
	
	public static final class UserRole {
		public static final String SUPER_ADMIN 			= "SA";
		public static final String HEAD_MEMBER 			= "HM";
		public static final String TRANSACTION_MEMBER 	= "MT";
		public static final String ADVISOR			 	= "AD";
	}
	
	public static final String EMPTY_KEYWORD = "";
	
	public static final class NotificationConstant {
		public static final Integer UNREAD = 0;
		public static final Integer READ = 1;
		public static final Integer REPLIED = 2;
		
		public static final String UNREAD_STR = "unread";
		public static final String READ_STR = "read";
		public static final String REPLIED_STR = "replied";
		
		public static final Map<Integer, String> NOTIFICATION_MAP = new HashMap<>();
		static {
			NOTIFICATION_MAP.put(UNREAD, UNREAD_STR);
			NOTIFICATION_MAP.put(READ, READ_STR);
			NOTIFICATION_MAP.put(REPLIED, REPLIED_STR);
		}
	}
	
	public static final class StatusAdvisory{
		public static final Integer CLOSED = 0;
		public static final Integer NEW = 1;
		public static final Integer OPEN = 2;

		public static final Integer ANSWERED = 3;
	}
	
	public static final String DEFAULT_TITLE_RESEARCH = "-- no title --";
	
	public static final class TabResearchManagement{
		public static final Integer ORGANIZER = 1;
		public static final Integer OFFICER = 2;
		public static final Integer PRE = 3;
		public static final Integer IMPLEMENTATION = 4;
		public static final Integer PASCA = 5;
		public static final Integer SUMMARY = 6;
	}
	
	public static final class AdvisoryMenuType{
		public static final Integer POST = 1;
		public static final Integer LINk = 2;
	}
	
	public static final class LinkUrlType{
		public static final String FOOTER = "FT";
		public static final String HOUSING_INDEX = "HI";
	}
	
	public static final class LookupUsage{
		public static final String CONTACT = "CONTACT";
	}
	
	public static final class PagesType{
		public static final String OTHER = "OT";
		public static final String ABOUT_US = "AU";
		public static final String PILAR = "PL";
		public static final String TERM_CONDITION = "TC";
		public static final String PROGRAM_LEARNING = "PRL";
		public static final String PROGRAM_ADVISORY = "PRA";
		public static final String PROGRAM_RESEARCH_DEVELOPMENT = "PRR";
		
		public static final String OTHER_STR = "Other";
		public static final String ABOUT_US_STR = "About Us";
		public static final String PILAR_STR = "Pilar";
		public static final String TERM_CONDITION_STR = "Term & Condition";
		
		public static final Map<String, String> PAGES_MAP = new HashMap<>();
		static {
			PAGES_MAP.put(OTHER, OTHER_STR);
			PAGES_MAP.put(ABOUT_US, ABOUT_US_STR);
			PAGES_MAP.put(PILAR, PILAR_STR);
			PAGES_MAP.put(TERM_CONDITION, TERM_CONDITION_STR);
		}
	}
	
	public static final class CategoryType{
		public static final String ALL = "ALL";
		public static final String LEARNING = "LG";
		public static final String ADVISORY = "ADV";
		public static final String RESEARCH_DEVELOPMENT = "RND";
		public static final String FAQ = "FAQ";
	}
	
	public static final class ThumbnailsDimension{
		public static final class FeatureImage{
			public static final int WIDTH = 600;
			public static final int HEIGHT = 400;	
		}
		public static final class Photo{
			public static final int WIDTH = 200;
			public static final int HEIGHT = 200;	
		}
	}
	
	public static final class Period{
		public static final String PAST = "P";
		public static final String FUTURE = "F";
	}
	
	public static final class IntegrationScriptType{
		public static final Integer GLOBAL = 0;
		public static final Integer SPESIFIC = 1;
	}
	
	public static final class IntegrationScriptPosition{
		public static final Integer HEADER = 1;
		public static final Integer FOOTER = 2;
		public static final String HEADER_STR = "Header";
		public static final String FOOTER_STR = "Footer";
		public static final Map<Integer, String> POSITION_MAP = new HashMap<>();
		static {
			POSITION_MAP.put(HEADER, HEADER_STR);
			POSITION_MAP.put(FOOTER, FOOTER_STR);
		}
	}
	
	public static final class BackgroundImageSize{
		public static final Integer SMALL = 1;
		public static final Integer BIG = 2;
		public static final String SMALL_STR = "Small";
		public static final String BIG_STR = "Big";
	}
}