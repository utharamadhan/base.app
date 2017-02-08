package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchImplementation;

import java.util.List;

public interface IResearchImplementationService extends MaintenanceService<ResearchImplementation> {

	public List<ResearchImplementation> findByFkResearch(Long fkResearch) throws SystemException;

}
