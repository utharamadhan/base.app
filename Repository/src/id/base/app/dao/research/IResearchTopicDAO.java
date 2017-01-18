package id.base.app.dao.research;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.research.ResearchTopic;

import java.util.List;


public interface IResearchTopicDAO extends IBaseDAO<ResearchTopic> {
	
	public List<ResearchTopic> findAllResearchTopicTitle() throws SystemException;

}
