package id.padiku.app.service.party;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyCompany;
import id.padiku.app.valueobject.party.PartyRole;

import java.util.List;

public interface IPartyService extends MaintenanceService<Party>{
	
	public List<Party> findAllPartyByRole(Long pkCompany, String roleCode, String keyword);
	
	public PartyRole findPartyRole(Long pkParty) throws SystemException;
	
	public PartyRole findPartyRole(String partyRoleCode, Long pkParty) throws SystemException;
	
	public PartyCompany findPartyCompanyByPartyAndCompany(Long pkCompany, Long pkParty) throws SystemException;

}
