package id.base.app;

import java.util.ArrayList;
import java.util.List;

public class ILookupGroupConstant {

	public static final String CONTACT_TYPE				= "CONTACT_TYPE";
	public static final String COURSE_BASIC_INFO_FIELD 	= "COURSE_BASIC_INFO_FIELD";
	public static final String LOG_GROUP 				= "LOG_GROUP";
	public static final String PARTY_ROLE 				= "PARTY_ROLE";
	
	public static List<String> LOOKUP_GROUP_LIST = new ArrayList<>();
	static {
		LOOKUP_GROUP_LIST.add(CONTACT_TYPE);
		LOOKUP_GROUP_LIST.add(COURSE_BASIC_INFO_FIELD);
		LOOKUP_GROUP_LIST.add(LOG_GROUP);
		LOOKUP_GROUP_LIST.add(PARTY_ROLE);
	}
	
}