package id.base.app.service.frontEndDisplay;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Pages;

public interface IPagesService extends MaintenanceService<Pages> {

	public Pages findByPermalink(String permalink) throws SystemException;

	public List<Pages> findSimpleData(String type) throws SystemException;

}