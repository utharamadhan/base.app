package id.base.app.dao.faq;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.Faq;

import java.util.List;

public interface IFaqDAO extends IBaseDAO<Faq>{

	public List<Faq> findFaqListForView() throws SystemException;
	
}