package id.padiku.app.service.transaction;

import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.procurement.TransIn;

public interface ITransInService extends MaintenanceService<TransIn>{

	public Long countTransIn(Long pkCompany, String transInSourceType);

}
