package id.base.app.service.frontEndDisplay;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.Pages;

public interface IPagesService extends MaintenanceService<Pages> {

	public Pages findByPermalink(String permalink) throws SystemException;

	public List<Pages> findSimpleData(String type) throws SystemException;

	public List<Pages> getLatestPages(List<String> types) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public void updateThumb(Long pkPages, String thumbURL) throws SystemException;

}