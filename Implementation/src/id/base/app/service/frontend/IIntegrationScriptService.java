package id.base.app.service.frontend;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.frontend.IntegrationScript;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface IIntegrationScriptService extends MaintenanceService<IntegrationScript> {

	public List<IntegrationScript> findByUrl(String url) throws SystemException;

	public List<IntegrationScript> findGlobalScript() throws SystemException;

}
