package id.base.app.dao.frontend;

import java.util.List;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.frontend.IntegrationScript;

public interface IIntegrationScriptDAO extends IBaseDAO<IntegrationScript> {

	public List<IntegrationScript> findByUrl(String url) throws SystemException;

}