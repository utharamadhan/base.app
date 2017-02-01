package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchMemo;

import java.util.List;

public interface IResearchMemoService extends MaintenanceService<ResearchMemo> {

	public List<ResearchMemo> findMemoByFkResearch(Long fkResearch) throws SystemException;

}
