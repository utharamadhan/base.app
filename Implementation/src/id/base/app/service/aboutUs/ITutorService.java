package id.base.app.service.aboutUs;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.aboutUs.Tutor;

import java.util.List;

public interface ITutorService extends MaintenanceService<Tutor> {
	
	public List<Tutor> findAllTutorCodeAndName() throws SystemException;

}
