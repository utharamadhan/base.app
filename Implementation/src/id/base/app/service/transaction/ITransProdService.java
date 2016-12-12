package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.production.TransProd;

import java.util.List;

public interface ITransProdService extends MaintenanceService<TransProd>{

	public Long countTransProd(Long pkCompany);

	public List<TransProd> findAllByYearMonth(Long pkCompany, Integer year, Integer month)
			throws SystemException;
	
}
