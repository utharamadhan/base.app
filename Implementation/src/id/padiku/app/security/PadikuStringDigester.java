package id.padiku.app.security;

import id.padiku.app.SystemParameter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PadikuStringDigester implements IStringDigester {	
	
	private PasswordEncoder passwordEncoder;
	
	private PasswordEncoder getPasswordEncode() {
		if(this.passwordEncoder == null){
			passwordEncoder = new BCryptPasswordEncoder();
		}
		return this.passwordEncoder;
	}
	
	public PadikuStringDigester(){
		super();
	}
	
	@Override
	public String digest(String text) {
		for(int i=0;i<SystemParameter.ENCRYPT_ITERATION_NUMBER;i++) {
			text = getPasswordEncode().encode(text);
			i++;
		}
		return text;
	}
	
	@Override
	public String digest(String text, Integer iterationTime) {
		int time = iterationTime != null && iterationTime > 0 ? iterationTime : 1;
		for(int i=0;i<time;i++) {
			text = getPasswordEncode().encode(text);
			i++;
		}
		return text;
	}

	@Override
	public boolean matches(String string1, String string2) {
		return getPasswordEncode().matches(string1, string2);
	}
	
}
