package id.base.app.dao.aboutUs;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.aboutUs.Engagement;

import java.util.List;


public interface IEngagementDAO extends IBaseDAO<Engagement> {

	public List<Engagement> findLatest(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public Engagement findByPermalink(String permalink) throws SystemException; 
}
