package id.padiku.app.service.authentication;

import id.padiku.app.LoginSession;
import id.padiku.app.service.AuthenticationService;
import id.padiku.app.valueobject.AppUser;

public abstract class BaseAuthentication implements AuthenticationService<LoginSession> {

	protected LoginSession buildLoginSession(AppUser appUser) {
		LoginSession loginSession = new LoginSession();
		loginSession.setPkAppUser(appUser.getPkAppUser());
		loginSession.setUserName(appUser.getUserName());
		loginSession.setEmail(appUser.getEmail());
		loginSession.setName(appUser.getParty().getName());
		loginSession.setUserType(appUser.getUserType());
		loginSession.setFlagSuperUser(Boolean.TRUE);
		return loginSession;
	}
}