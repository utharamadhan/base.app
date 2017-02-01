package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchTimePlanning;

import java.util.List;

public interface IResearchTimePlanningService extends MaintenanceService<ResearchTimePlanning> {

	public List<ResearchTimePlanning> findTimePlanningByFkResearch(Long fkResearch) throws SystemException;

}
