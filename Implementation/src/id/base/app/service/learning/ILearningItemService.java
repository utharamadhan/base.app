package id.base.app.service.learning;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.learning.LearningItem;

import java.util.List;
import java.util.Map;

public interface ILearningItemService extends MaintenanceService<LearningItem> {
	
	public List<LearningItem> findAllCourseCodeName() throws SystemException;
	
	public List<LearningItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException;

	public LearningItem findByPermalink(String permalink) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public void updateThumb(Long pkLearningItem, String thumbURL) throws SystemException;

	public List<LearningItem> findForSelectEligibleReg(Long pkCategory) throws SystemException;

}
