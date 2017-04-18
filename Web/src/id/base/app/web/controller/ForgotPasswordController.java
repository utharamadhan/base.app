package id.base.app.web.controller;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.AppUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author USER
 *
 */
@Scope(value="request")
@Controller
@RequestMapping(value="/forgotPassword")
public class ForgotPasswordController {
	
	@RequestMapping(method=RequestMethod.POST)
	public String landingPagePost(HttpServletRequest request, HttpServletResponse response) {
		return "/forgotPassword";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/submitForgotPassword")
	@ResponseBody
	public Map<String, Object> submitForgotPassword(@RequestParam(value="email") String email, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<ErrorHolder> errors = new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_FORGOT_PASSWORD, String.class).performPost("/sendMail", email);
			if(errors != null && errors.size() > 0) {
				map.put(SystemConstant.ERROR_LIST, errors);
			} else {
				map.put("loginURL", SystemConstant.LOGIN_URL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/submitNewPassword")
	@ResponseBody
	public Map<String, Object> submitNewPassword(@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password, 
			@RequestParam(value="confirmPassword") String confirmPassword, 
			@RequestParam(value="token") String token, 
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			AppUser user = AppUser.getInstance();
				user.setEmail(email);
				user.setPassword(password);
				user.setPasswordConfirmation(confirmPassword);
				user.setResetPasswordToken(token);
			List<ErrorHolder> errors = new SpecificRestCaller<AppUser>(RestConstant.REST_SERVICE, RestConstant.RM_FORGOT_PASSWORD, AppUser.class).performPost("/updatePassword", user);
			if(errors != null && errors.size() > 0) {
				map.put(SystemConstant.ERROR_LIST, errors);
			} else {
				map.put("loginURL", SystemConstant.LOGIN_URL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/updatePassword")
	public String updatePassword(@RequestParam(value="email") final String email, @RequestParam(value="token") final String token, ModelMap model, HttpServletRequest request, HttpServletResponse respone) {
		try {
			Boolean isValid = new SpecificRestCaller<Boolean>(RestConstant.REST_SERVICE, RestConstant.RM_FORGOT_PASSWORD, Boolean.class).executeGet(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/validateToken/{email}/{token}";
				}
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("email", email);
						map.put("token", token);
					return map;
				}
			});
			if(!isValid) {
				return "/forgotPasswordInvalidToken";	
			} else {
				model.addAttribute("email", email);
				model.addAttribute("token", token);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "/forgotPasswordInvalidToken";
		}
		return "/forgotPasswordReset";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/blank")
	public String blank(){
		return "/blank";
	}
}