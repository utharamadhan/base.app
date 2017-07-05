package id.base.app.service.research;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.Research;

public interface IResearchService extends MaintenanceService<Research> {

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public Research findByPermalink(String permalink) throws SystemException;

}
