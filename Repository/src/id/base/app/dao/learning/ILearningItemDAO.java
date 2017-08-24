package id.base.app.dao.learning;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.learning.LearningItem;

import java.util.List;
import java.util.Map;


public interface ILearningItemDAO extends IBaseDAO<LearningItem> {
	
	public List<LearningItem> findAllCourseCodeName() throws SystemException;

	public List<LearningItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public LearningItem findByPermalink(String permalink) throws SystemException;

	public void updateAnyUrl(Long pkLearningItem, LearningItem learningItem) throws SystemException;

	public List<LearningItem> findForSelectEligibleReg(Long pkCategory) throws SystemException;
	
	public List<LearningItem> findForSelectEligibleRegByCategoryPermalink(String categoryPermalink) throws SystemException;

	public List<LearningItem> findForSelectByType(String type) throws SystemException;

}