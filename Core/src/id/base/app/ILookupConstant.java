/*
 * Created on Jul 5, 2005
 *
 */
package id.base.app;

import id.base.app.rest.RestConstant;

import java.util.HashMap;
import java.util.Map;

public interface ILookupConstant {

	public static final class UserStatus {
		public static final Integer NEW = 0;
		public static final Integer ACTIVE = 1;
		public static final Integer INACTIVE = 2;
		public static final Integer LOCK = 3;
		
		public static final String NEW_STR = "New";
		public static final String ACTIVE_STR = "Active";
		public static final String INACTIVE_STR	= "Inactive";
		public static final String LOCK_STR	= "Lock";
		
		public static final HashMap<Integer, String> USER_STATUS_MAP = new HashMap<Integer, String>();
		static{
			USER_STATUS_MAP.put(NEW, NEW_STR);
			USER_STATUS_MAP.put(ACTIVE, ACTIVE_STR);
			USER_STATUS_MAP.put(INACTIVE, INACTIVE_STR);
			USER_STATUS_MAP.put(LOCK, LOCK_STR);
		}
	}
	
	public static final class Status {
		public static final Integer DELETE 	= 0;
		public static final Integer DRAFT 	= 1;
		public static final Integer PUBLISH = 2;
		
		public static final String DELETE_STR = "Delete";
		public static final String DRAFT_STR = "Draft";
		public static final String PUBLISH_STR = "Publish";
		
		public static final Map<Integer, String> STATUS_MAP = new HashMap<>();
		static {
			STATUS_MAP.put(DELETE, DELETE_STR);
			STATUS_MAP.put(DRAFT, DRAFT_STR);
			STATUS_MAP.put(PUBLISH, PUBLISH_STR);
		}
	}
	
	public static final class StatusContact {
		public static final Integer NEW = 0;
		public static final Integer READ = 1;
		public static final Integer ANSWER = 2;
	}
	
	public static final class EnrollmentStatus {
		public static final String ENROLLED 	= "EN";
		public static final String PASSED 		= "PA";
		public static final String FAILED 		= "FA";
		
		public static final String ENROLLED_STR = "enrolled";
		public static final String PASSED_STR = "passed";
		public static final String FAILED_STR = "failed";
		
		public static final Map<String, String> ENROLLMENT_STATUS_MAP = new HashMap<>();
		static {
			ENROLLMENT_STATUS_MAP.put(ENROLLED_STR, ENROLLED);
			ENROLLMENT_STATUS_MAP.put(PASSED_STR, PASSED);
			ENROLLMENT_STATUS_MAP.put(FAILED_STR, FAILED);
		}
	}
	
	public static final class FieldDataType {
		public static final int NUMERIC = 1;
		public static final int STRING 	= 2;
		public static final int DATE 	= 3;
		public static final int LOOKUP	= 4;
		public static final int LONG	= 5;
		public static final int EMAIL	= 6;
	}
	
	public static final class NotificationActionType {
		public static final String CONTACT_US 	= "CO";
		public static final String ADVISORY		= "AD";
		
		public static final String CONTACT_US_URL = RestConstant.WEB_ADMIN_SERVICE + "/contactUs/userContact/showListNotif/";
		
		public static final Map<String, String> URL_MAP = new HashMap<>();
		static {
			URL_MAP.put(CONTACT_US, CONTACT_US_URL);
		}
	}
    
	public static final class PartyContact {
		public static final String HANDPHONE = "HP";
		public static final String OFFICE_PHONE_NUMBER = "OP";
	}
	
    public static final class CategoryHelp {
		public static final String CALL_CENTER = "CC";
		public static final String PROGRAM = "PR";
		public static final String CONSULT = "CO";
	}
    
    public static final class LearningDisplay {
		public static final String FULL_SCREEN_WITHOUT_SIDEBAR = "FWS";
		public static final String FULL_SCREEN_SIDEBAR_RIGHT_DESCRIPTION = "FSR";
		public static final String NOT_FULL_SCREEN_SIDEBAR_RIGHT = "NFSR";
	}
    
}