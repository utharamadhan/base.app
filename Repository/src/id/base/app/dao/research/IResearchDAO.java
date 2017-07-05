package id.base.app.dao.research;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.research.Research;

import java.util.List;

public interface IResearchDAO extends IBaseDAO<Research> {

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public Research findByPermalink(String permalink) throws SystemException;

}