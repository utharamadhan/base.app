package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.production.TransProd;

import java.util.List;

public interface ITransProdService extends MaintenanceService<TransProd>{

	public Long countTransProd(Long pkCompany);

	public List<TransProd> findAllByYearMonth(Long pkCompany, Integer year, Integer month)
			throws SystemException;
	
}
