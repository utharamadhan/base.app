package id.base.app.dao.aboutUs;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.publication.DigitalBook;


public interface IEngagementDAO extends IBaseDAO<Engagement> {

	public List<Engagement> findLatest(int number) throws SystemException; 
}
