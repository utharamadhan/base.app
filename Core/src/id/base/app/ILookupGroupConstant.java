package id.base.app;

import java.util.ArrayList;
import java.util.List;

public class ILookupGroupConstant {

	public static final String ACTOR_PRODUCTION 		= "ACTOR_PRODUCTION";
	public static final String CONTACT_TYPE		 		= "CONTACT_TYPE";
	public static final String CURRENCY 				= "CURRENCY";
	public static final String FEE_TYPE				 	= "FEE_TYPE";
	public static final String GOODS_RECEIPT_FROM		= "GOODS_RECEIPT_FROM";
	public static final String ITEM 					= "ITEM";
	public static final String KONVERSI_SATUAN 			= "KONVERSI_SATUAN";
	public static final String LOG_GROUP 				= "LOG_GROUP";
	public static final String MACHINE_MODEL 			= "MACHINE_MODEL";
	public static final String MACHINE_TYPE 			= "MACHINE_TYPE";
	public static final String MACHINE_WEIGHTING 		= "MACHINE_WEIGHTING";
	public static final String MACHINE_CAPACITY_UOM 	= "MACHINE_CAPACITY_UOM";
	public static final String MACHINE_POWER_SOURCE 	= "MACHINE_POWER_SOURCE";
	public static final String MACHINE_OWNERSHIPS 		= "MACHINE_OWNERSHIPS";
	public static final String METODE 					= "METODE";
	public static final String PARTY_ROLE 				= "PARTY_ROLE";
	public static final String ITEM_TYPE 				= "ITEM_TYPE";
	public static final String TERM_OF_PAYMENT			= "TERM_OF_PAYMENT";
	public static final String TYPE_STOCK 				= "TYPE_STOCK";
	public static final String UOM 						= "UOM";
	public static final String WAREHOUSE_TYPE 			= "WAREHOUSE_TYPE";
	
	public static List<String> LOOKUP_GROUP_LIST = new ArrayList<>();
	static {
		LOOKUP_GROUP_LIST.add(ACTOR_PRODUCTION);
		LOOKUP_GROUP_LIST.add(CONTACT_TYPE);
		LOOKUP_GROUP_LIST.add(CURRENCY);
		LOOKUP_GROUP_LIST.add(FEE_TYPE);
		LOOKUP_GROUP_LIST.add(GOODS_RECEIPT_FROM);
		LOOKUP_GROUP_LIST.add(ITEM);
		LOOKUP_GROUP_LIST.add(KONVERSI_SATUAN);
		LOOKUP_GROUP_LIST.add(LOG_GROUP);
		LOOKUP_GROUP_LIST.add(MACHINE_MODEL);
		LOOKUP_GROUP_LIST.add(MACHINE_TYPE );
		LOOKUP_GROUP_LIST.add(MACHINE_WEIGHTING);
		LOOKUP_GROUP_LIST.add(MACHINE_CAPACITY_UOM);
		LOOKUP_GROUP_LIST.add(MACHINE_POWER_SOURCE);
		LOOKUP_GROUP_LIST.add(MACHINE_OWNERSHIPS);
		LOOKUP_GROUP_LIST.add(METODE);
		LOOKUP_GROUP_LIST.add(PARTY_ROLE);
		LOOKUP_GROUP_LIST.add(ITEM_TYPE);
		LOOKUP_GROUP_LIST.add(TERM_OF_PAYMENT);
		LOOKUP_GROUP_LIST.add(TYPE_STOCK);
		LOOKUP_GROUP_LIST.add(UOM);
		LOOKUP_GROUP_LIST.add(WAREHOUSE_TYPE);
	}
	
}