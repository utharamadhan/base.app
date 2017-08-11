package id.base.app;

import java.util.ArrayList;
import java.util.List;

public class ILookupGroupConstant {

	public static final String USER_STATUS				= "USER_STATUS";
	public static final String STATUS					= "STATUS";
	public static final String CONTACT_TYPE				= "CONTACT_TYPE";
	public static final String COURSE_BASIC_INFO_FIELD 	= "COURSE_BASIC_INFO_FIELD";
	public static final String COURSE_CATEGORY			= "COURSE_CATEGORY";
	public static final String ENROLLMENT_STATUS		= "ENROLLMENT_STATUS";
	public static final String GENDER					= "GENDER";
	public static final String LOG_GROUP 				= "LOG_GROUP";
	public static final String NOTIFICATION_ACTION_TYPE	= "NOTIFICATION_ACTION_TYPE";
	public static final String PARTY_ROLE 				= "PARTY_ROLE";
	public static final String STUDENT_STATUS			= "STUDENT_STATUS";
	public static final String CATEGORY_HELP			= "CATEGORY_HELP";
	public static final String LEARNING_METHOD			= "LEARNING_METHOD";
	public static final String LEARNING_ORGANIZER		= "LEARNING_ORGANIZER";
	public static final String LEARNING_PAYMENT			= "LEARNING_PAYMENT";
	public static final String LEARNING_DISPLAY			= "LEARNING_DISPLAY";
	
	public static List<String> LOOKUP_GROUP_LIST = new ArrayList<>();
	static {
		LOOKUP_GROUP_LIST.add(USER_STATUS);
		LOOKUP_GROUP_LIST.add(STATUS);
		LOOKUP_GROUP_LIST.add(CONTACT_TYPE);
		LOOKUP_GROUP_LIST.add(COURSE_BASIC_INFO_FIELD);
		LOOKUP_GROUP_LIST.add(ENROLLMENT_STATUS);
		LOOKUP_GROUP_LIST.add(GENDER);
		LOOKUP_GROUP_LIST.add(LOG_GROUP);
		LOOKUP_GROUP_LIST.add(PARTY_ROLE);
		LOOKUP_GROUP_LIST.add(STUDENT_STATUS);
		LOOKUP_GROUP_LIST.add(CATEGORY_HELP);
		LOOKUP_GROUP_LIST.add(LEARNING_METHOD);
		LOOKUP_GROUP_LIST.add(LEARNING_ORGANIZER);
		LOOKUP_GROUP_LIST.add(LEARNING_PAYMENT);
		LOOKUP_GROUP_LIST.add(LEARNING_DISPLAY);
	}	
}