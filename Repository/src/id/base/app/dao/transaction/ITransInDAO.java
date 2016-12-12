package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.procurement.TransIn;

public interface ITransInDAO extends IBaseDAO<TransIn>{

	public Long countTransIn(Long pkCompany, String transInSourceType)
			throws SystemException;

}
