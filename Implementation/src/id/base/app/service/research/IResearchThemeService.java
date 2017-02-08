package id.base.app.service.research;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.research.ResearchTheme;

import java.util.List;

public interface IResearchThemeService extends MaintenanceService<ResearchTheme> {
	
	public List<ResearchTheme> findAllResearchThemeTitle() throws SystemException;

}
