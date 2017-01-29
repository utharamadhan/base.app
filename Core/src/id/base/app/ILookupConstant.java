/*
 * Created on Jul 5, 2005
 *
 */
package id.base.app;

import id.base.app.rest.RestConstant;

import java.util.HashMap;
import java.util.Map;

public interface ILookupConstant {

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
		
		public static final String CONTACT_US_URL = RestConstant.WEB_ADMIN_SERVICE + "/contactUs/userContact/showList";
		
		public static final Map<String, String> URL_MAP = new HashMap<>();
		static {
			URL_MAP.put(CONTACT_US, CONTACT_US_URL);
		}
		
	}
    
	public static final class PartyContact {
		public static final String HANDPHONE = "HP";
		public static final String OFFICE_PHONE_NUMBER = "OP";
	}
	
    public static final class PartyRole {
		public static final String SUPPLIER		= "SP";
		public static final String TRANSPORTER 	= "TR";
		public static final String CUSTOMER		= "CS";
		public static final String PRODUSEN		= "PD";
		
		public static final String SUPPLIER_STR		= "Pemasok";
		public static final String TRANSPORTER_STR 	= "Pengangkut";
		public static final String CUSTOMER_STR		= "Pelanggan";
		public static final String PRODUSEN_STR		= "Produsen";
		
		public static final Map<String, String> partyRoleMap = new HashMap<>();
		static{
			partyRoleMap.put(SUPPLIER,  SUPPLIER_STR);
			partyRoleMap.put(TRANSPORTER, TRANSPORTER_STR);
			partyRoleMap.put(CUSTOMER,  CUSTOMER_STR);
			partyRoleMap.put(PRODUSEN,  PRODUSEN_STR);
		}
	}
    
    public static final class CategoryHelp {
		public static final String CALL_CENTER = "CC";
		public static final String PROGRAM = "PR";
		public static final String CONSULT = "CO";
	}
}