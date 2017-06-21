package id.base.app.dao.advisory;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.Category;

import java.util.List;

public interface ICategoryDAO extends IBaseDAO<Category> {

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;
	
}