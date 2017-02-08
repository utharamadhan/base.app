package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchOfficer;

import java.util.List;

public interface IResearchOfficerService extends MaintenanceService<ResearchOfficer> {

	public List<ResearchOfficer> findByFkResearch(Long fkResearch) throws SystemException;

}
