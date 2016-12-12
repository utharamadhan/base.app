package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.procurement.TransIn;

public interface ITransInDAO extends IBaseDAO<TransIn>{

	public Long countTransIn(Long pkCompany, String transInSourceType)
			throws SystemException;

}
