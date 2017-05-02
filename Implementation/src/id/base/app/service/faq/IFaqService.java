package id.base.app.service.faq;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Faq;

public interface IFaqService extends MaintenanceService<Faq> {

	public List<Faq> findFaqListForView() throws SystemException;
	
}