package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchBudgeting;

import java.util.List;

public interface IResearchBudgetingService extends MaintenanceService<ResearchBudgeting> {

	public List<ResearchBudgeting> findBudgetingByFkResearch(Long fkResearch) throws SystemException;

}
