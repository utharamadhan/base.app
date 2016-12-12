/*
 * Created on Jul 5, 2005
 *
 */
package id.base.app;

import java.util.HashMap;
import java.util.Map;

public interface ILookupConstant {

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
    
    public static final class FieldDataType {
		public static final int NUMERIC = 1;
		public static final int STRING 	= 2;
		public static final int DATE 	= 3;
		public static final int LOOKUP	= 4;
		public static final int LONG	= 5;
		public static final int EMAIL	= 6;
	}
    
    public static final class MasterFeeType {
    	public static final String PEMBELIAN_BAHAN_BAKU_PRODUKSI = "BBBP";
    	public static final String PEMBELIAN_BARANG_PENUNJANG_PRODUKSI = "BBPP";
    	public static final String PRODUKSI = "PROD";
    	public static final String PENJUALAN_BAHAN_BAKU_PRODUKSI = "SBBP";
    	public static final String PENJUALAN_BARANG_PENUNJANG_PRODUKSI = "SBPP";
    }
    
    public static final class ActorProduction {
    	public static final String MANDIRI = "M";
    	public static final String THIRD_PARTY = "TP";
    }
    
    public static final class Uom {
    	public static final String KG = "KG";
    	public static final String TON = "TON";
    	
    	public static final String KG_TO_TON = "KG_TON_TON";
    	public static final String TON_TO_KG = "TON_TO_KG";
    	
    	public static final Map<String, String> KG_CONVERTION_MAP = new HashMap<String, String>();
    	static{
    		KG_CONVERTION_MAP.put(TON, KG_TO_TON);
    	}
    	
    	public static final Map<String, String> TON_CONVERTION_MAP = new HashMap<String, String>();
    	static{
    		TON_CONVERTION_MAP.put(KG, TON_TO_KG);
    	}
    }
    
}
