package id.base.app.controller.research;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.research.IResearchTopicService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.research.ResearchTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_RESEARCH_TOPIC)
public class ResearchTopicController extends SuperController<ResearchTopic>{
	
	@Autowired
	private IResearchTopicService researchTopicService;
	

	@Override
	public ResearchTopic validate(ResearchTopic anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchTopic.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(ResearchTopic.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<ResearchTopic> getMaintenanceService() {
		return researchTopicService;
	}
	
	@Override
	public ResearchTopic preUpdate(ResearchTopic anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
	@RequestMapping(value="/findAllResearchTopicCodeAndName")
	@ResponseBody
	public List<ResearchTopic> findAllResearchTopicCodeAndName() {
		return researchTopicService.findAllResearchTopicCodeAndName();
	}
	
}
