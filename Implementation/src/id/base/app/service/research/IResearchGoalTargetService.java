package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchGoalTarget;

import java.util.List;

public interface IResearchGoalTargetService extends MaintenanceService<ResearchGoalTarget> {

	public List<ResearchGoalTarget> findGoalTargetByFkResearch(Long fkResearch) throws SystemException;

}
