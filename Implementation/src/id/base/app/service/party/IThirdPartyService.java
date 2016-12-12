package id.base.app.service.party;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.master.VWCompanyThirdParty;
import id.base.app.valueobject.party.Party;

public interface IThirdPartyService extends MaintenanceService<VWCompanyThirdParty>{
	
	public void saveOrUpdateThirdParty(Party anObject) throws SystemException;
	
	public Party getThirdCompanyParty(Long maintenancePK) throws SystemException;

}
