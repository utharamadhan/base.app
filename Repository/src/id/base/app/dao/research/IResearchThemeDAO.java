package id.base.app.dao.research;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.research.ResearchTheme;

import java.util.List;

public interface IResearchThemeDAO extends IBaseDAO<ResearchTheme> {
	
	public List<ResearchTheme> findAllResearchThemeTitle() throws SystemException;

}
