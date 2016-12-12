package id.base.app.service.transaction;

import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.procurement.TransIn;

public interface ITransInService extends MaintenanceService<TransIn>{

	public Long countTransIn(Long pkCompany, String transInSourceType);

}
