package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchTopic;

import java.util.List;

public interface IResearchTopicService extends MaintenanceService<ResearchTopic> {
	
	public List<ResearchTopic> findAllResearchTopicTitle() throws SystemException;

}
