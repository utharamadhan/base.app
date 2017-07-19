package id.base.app.service.advisory;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Category;

import java.util.List;

public interface ICategoryService extends MaintenanceService<Category> {

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public List<Category> findByType(String type) throws SystemException;

	public String getFirstPermalinkData(String type) throws SystemException;

	public Category findSimpleDataByPermalink(String permalink) throws SystemException;

	public List<Category> findSimpleDataForList(String type) throws SystemException;
	
	public void updateThumb(Long pkCategory, String thumbURL) throws SystemException;

}