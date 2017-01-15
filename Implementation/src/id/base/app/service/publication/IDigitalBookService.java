package id.base.app.service.publication;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.publication.DigitalBook;

public interface IDigitalBookService extends MaintenanceService<DigitalBook> {
	
	public List<DigitalBook> findLatestEbook(int number) throws SystemException;

}
