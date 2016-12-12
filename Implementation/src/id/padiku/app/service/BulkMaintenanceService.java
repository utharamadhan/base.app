package id.padiku.app.service;

import id.padiku.app.exception.SystemException;

import java.util.List;

public interface BulkMaintenanceService<T> extends MaintenanceService<T> {

	public void saveOrUpdate(List<T> maintenanceObjects) throws SystemException ;
	
}
