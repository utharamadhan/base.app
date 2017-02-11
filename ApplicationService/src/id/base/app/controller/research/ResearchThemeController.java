package id.base.app.controller.research;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.research.IResearchThemeService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.research.ResearchTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_RESEARCH_THEME)
public class ResearchThemeController extends SuperController<ResearchTheme>{
	
	@Autowired
	private IResearchThemeService researchThemeService;
	
	@Override
	public ResearchTheme validate(ResearchTheme anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchTheme.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(ResearchTheme.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<ResearchTheme> getMaintenanceService() {
		return researchThemeService;
	}
	
	@Override
	public ResearchTheme preUpdate(ResearchTheme anObject) throws SystemException{
		return validate(anObject);
	}
	
	@RequestMapping(value="/findAllResearchThemeTitle")
	@ResponseBody
	public List<ResearchTheme> findAllResearchThemeTitle() {
		return researchThemeService.findAllResearchThemeTitle();
	}	
}
