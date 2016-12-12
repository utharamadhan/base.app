package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.valueobject.production.TransProd;

public interface ITransProdDAO extends IBaseDAO<TransProd>{

	public Long countTransProd(Long pkCompany);

}
