package id.base.app.service.advisory;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Category;

import java.util.List;

public interface ICategoryService extends MaintenanceService<Category> {

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

}