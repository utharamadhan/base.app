package id.base.app.dao.frontEndDisplay;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.Pages;

public interface IPagesDAO extends IBaseDAO<Pages> {

	public Pages findByPermalink(String permalink) throws SystemException;

}