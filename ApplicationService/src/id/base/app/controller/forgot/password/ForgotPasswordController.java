package id.base.app.controller.forgot.password;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.mail.EmailAPI;
import id.base.app.service.user.IUserService;
import id.base.app.util.DateTimeFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.AppUser;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_FORGOT_PASSWORD)
public class ForgotPasswordController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	@Qualifier("SMTPMailService")
	private EmailAPI mailService;
	
	@RequestMapping(method=RequestMethod.GET, value="/validateToken/{email}/{token}")
	@ResponseBody
	public Boolean validateToken(@PathVariable("email") String email, @PathVariable("token") String token) throws SystemException {
		return userService.validateResetPasswordToken(email, token);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sendMail")
	@ResponseBody
	public void sendMail(@RequestBody String email) throws SystemException {
		try {
			AppUser user = validate(email);
			mailService.sendMail(new ArrayList<String>(Arrays.asList(email)), "www.hfc.com", "Forgot Password", constructEmailContent(user) , null);
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder(e.getMessage()))));
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updatePassword")
	@ResponseBody
	public void updatePassword(@RequestBody AppUser user) throws SystemException {
		try {
			user = validate(user);
			userService.saveOrUpdate(user);
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder(e.getMessage()))));
		}
	}
	
	private String constructEmailContent(AppUser appUser) {
		appUser.setResetPasswordToken(generateToken(Calendar.getInstance().getTime()));
		userService.saveOrUpdate(appUser);
		return "<a href=\""+SystemConstant.UPDATE_PASSWORD_URL + "email="+appUser.getEmail()+"&token="+appUser.getResetPasswordToken()+"\"/>Reset Password</a>";
	}
	
	private String generateToken(Date currentDate) {
		StringBuilder sb = new StringBuilder();
		try {
			String str1 = DateTimeFunction.date2String(currentDate, SystemConstant.SYSTEM_DATE_TIME_NO_DELIMITER);
			MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] array = md.digest(str1.getBytes("UTF-8"));
		    for (byte b : array) {
		        sb.append(String.format("%02X", b));
		    }
		} catch (Exception e) {}
		return sb.toString();
	}
	
	public AppUser validate(String email) throws SystemException {
		AppUser user = userService.findByEmail(email);
		if(user == null) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder("email not found"))));
		}
		return user;
	}
	
	public AppUser validate(AppUser user) {
		AppUser objUser = userService.findByEmail(user.getEmail());
		if(objUser == null || objUser.getResetPasswordToken() == null) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder("user not found"))));
		}
		if(objUser.getResetPasswordToken().equals(user.getResetPasswordToken())) {
			objUser.setResetPasswordToken(null);
		} else {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder("invalid token"))));
		}
		if(StringFunction.isEmpty(user.getPassword()) || StringFunction.isEmpty(user.getPasswordConfirmation())) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder("please fill these two fields with same value"))));
		} else if (!user.getPassword().equals(user.getPasswordConfirmation())) {
			throw new SystemException(new ArrayList<ErrorHolder>(Arrays.asList(new ErrorHolder("please fill these two fields with same value"))));
		} else {
			objUser.setPassword(user.getPassword());
		}
		objUser.setIsNewPassword(Boolean.TRUE);
		return objUser; 
	}

}
