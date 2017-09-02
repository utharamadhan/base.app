package id.base.app.util;

import id.base.app.SystemParameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class EmailFunction {
	public static boolean isAddressValid(String emailAddress) {
		String addr = StringFunction.trim(emailAddress);
		try {
			new InternetAddress(addr, SystemParameter.STRICT_EMAIL_ADDRESS);
		} catch (AddressException e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * validate email address using regular expression
	 */
	public static boolean isAddressValidRegex(String emailAddress){
		String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(emailAddress);
		
		return matcher.matches();
	}
}
