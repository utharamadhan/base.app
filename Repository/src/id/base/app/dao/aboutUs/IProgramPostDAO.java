package id.base.app.dao.aboutUs;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.aboutUs.ProgramPost;


public interface IProgramPostDAO extends IBaseDAO<ProgramPost> {

	public List<ProgramPost> findLatest(int number) throws SystemException; 
}
