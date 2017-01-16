package id.base.app.service.aboutUs;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.aboutUs.Engagement;

public interface IEngagementService extends MaintenanceService<Engagement> {

	public List<Engagement> findLatest(int number) throws SystemException; 
}
