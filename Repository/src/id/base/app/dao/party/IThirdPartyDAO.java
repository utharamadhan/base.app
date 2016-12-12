package id.base.app.dao.party;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.VWCompanyThirdParty;
import id.base.app.valueobject.party.Party;

public interface IThirdPartyDAO extends IBaseDAO<VWCompanyThirdParty> {
	
	public Party getThirdCompanyParty(Long maintenancePK) throws SystemException;

}
