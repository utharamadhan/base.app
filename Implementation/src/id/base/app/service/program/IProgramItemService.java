package id.base.app.service.program;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.program.ProgramItem;

import java.util.List;
import java.util.Map;

public interface IProgramItemService extends MaintenanceService<ProgramItem> {
	
	public List<ProgramItem> findAllCourseCodeName() throws SystemException;
	
	public List<ProgramItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException;

	public ProgramItem findByPermalink(String permalink) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public void updateAnyUrl(Long pkProgramItem, ProgramItem programItem) throws SystemException;

	public List<ProgramItem> findForSelectEligibleReg(Long pkCategory) throws SystemException;
	
	public List<ProgramItem> findForSelectEligibleRegByCategoryPermalink(String categoryPermalink) throws SystemException;

	public List<ProgramItem> findForSelectByType(String type) throws SystemException;

}
