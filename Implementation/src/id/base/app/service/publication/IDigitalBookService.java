package id.base.app.service.publication;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.publication.DigitalBook;

import java.util.List;

public interface IDigitalBookService extends MaintenanceService<DigitalBook> {
	
	public List<DigitalBook> findLatestEbook(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public DigitalBook findByPermalink(String permalink) throws SystemException;

}
