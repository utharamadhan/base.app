package id.base.app.dao.aboutUs;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.aboutUs.Tutor;


public interface ITutorDAO extends IBaseDAO<Tutor> {
	
	public List<Tutor> findAllTutorCodeAndName() throws SystemException;

}
