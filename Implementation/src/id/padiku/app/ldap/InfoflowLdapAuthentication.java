package id.padiku.app.ldap;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.AppUser;

import javax.naming.directory.Attributes;

/**
 * for testing purpose internal infoflow
 *  
 */
public class InfoflowLdapAuthentication extends BaseSpringLdap<AppUser> {

	public Attributes buildAttributes(AppUser user) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean authenticate(AppUser user, String password) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public AppUser mapFromAttributes(Attributes attrs) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String buildDn(AppUser user) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
