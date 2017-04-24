package id.base.app.util;

import id.base.app.JSONConstant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneralFunctions {
	
	public static String getDefaultPassword(int min, int max){
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		Random random = new Random(System.currentTimeMillis());
		int result = min + random.nextInt(max - min + 1);
		return myRandom.substring(0,result);
	}
	
	public static boolean safeEqual(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return true;
		}
		if((obj1 instanceof String) || (obj2 instanceof String)){
			if(obj1==null) obj1="";
			if(obj2==null) obj2="";
		}else if (obj1 == null || obj2 == null) {
			return false;
		}
		return obj1.equals(obj2);
	}
	
	public static Map<String, String> getTokenMap(String token) {
		Map<String, String> map = new HashMap<>();
		try {
			String jsonDecoded = decodeFromToken(token);
			map = new ObjectMapper().readValue(jsonDecoded, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String decodeFromToken(String token) {
		try {
			byte[] valueDecoded= Base64.decodeBase64(token.getBytes());
			String decoded1 = new String(valueDecoded);
			decoded1 = decoded1.replace(JSONConstant.RANDOM_TOKEN, "");
			return new String(Base64.decodeBase64(decoded1.getBytes()));
		} catch (Exception e) {}
		return null;
	}

	public static int getExpiryFromToken(String token) {
		int expire = 60*15;
		try {
			if(StringFunction.isNotEmpty(token)){
				Map<String, String> map = getTokenMap(token);
				String yyyyMMddHHmm = map.get("expiredTime");
				Calendar tcal = Calendar.getInstance();
				Date d = null;
			    if (yyyyMMddHHmm != null && yyyyMMddHHmm.length()>0) {
			        d = (new SimpleDateFormat("yyyyMMddHHmm")).parse(yyyyMMddHHmm);
			        tcal.setTime(d);
			    }
				Calendar cal = Calendar.getInstance();
				if(d!=null){
					expire = getDiff(expire,cal,tcal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expire;
	}
	
	private static int getDiff(int defValue, Calendar now, Calendar then){
		long milliseconds1 = now.getTimeInMillis();
	    long milliseconds2 = then.getTimeInMillis();
	    long diff = milliseconds2 - milliseconds1;
	    long diffSeconds = diff / 1000;
	    if(defValue<(int)diffSeconds){
	    	defValue = (int) diffSeconds;
	    }
	    return defValue;
	}
	
}
