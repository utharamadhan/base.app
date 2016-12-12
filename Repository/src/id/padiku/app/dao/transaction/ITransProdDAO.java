package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.valueobject.production.TransProd;

public interface ITransProdDAO extends IBaseDAO<TransProd>{

	public Long countTransProd(Long pkCompany);

}
