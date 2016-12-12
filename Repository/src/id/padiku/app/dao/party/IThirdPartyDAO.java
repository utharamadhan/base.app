package id.padiku.app.dao.party;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;

public interface IThirdPartyDAO extends IBaseDAO<VWCompanyThirdParty> {
	
	public Party getThirdCompanyParty(Long maintenancePK) throws SystemException;

}
