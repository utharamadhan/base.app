package id.base.app.dao.publication;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.publication.DigitalBook;


public interface IDigitalBookDAO extends IBaseDAO<DigitalBook> {

	public List<DigitalBook> findLatestEbook(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink)
			throws SystemException;

	public DigitalBook findByPermalink(String permalink) throws SystemException; 
}
