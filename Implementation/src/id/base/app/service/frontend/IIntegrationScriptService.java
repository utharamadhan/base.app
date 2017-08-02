package id.base.app.service.frontend;

import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.frontend.IntegrationScript;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface IIntegrationScriptService extends MaintenanceService<IntegrationScript> {

}
