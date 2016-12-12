package id.padiku.app.service.party;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;

public interface IThirdPartyService extends MaintenanceService<VWCompanyThirdParty>{
	
	public void saveOrUpdateThirdParty(Party anObject) throws SystemException;
	
	public Party getThirdCompanyParty(Long maintenancePK) throws SystemException;

}
