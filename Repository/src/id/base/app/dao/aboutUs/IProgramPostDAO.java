package id.base.app.dao.aboutUs;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.aboutUs.ProgramPost;

import java.util.List;


public interface IProgramPostDAO extends IBaseDAO<ProgramPost> {

	public List<ProgramPost> findLatest(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public ProgramPost findByPermalink(String permalink) throws SystemException; 
}
