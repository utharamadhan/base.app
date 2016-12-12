package id.base.app.valueobject.party;

import id.base.app.valueobject.master.Company;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARTY_COMPANY")
public class PartyCompany implements Serializable {

	private static final long serialVersionUID = -2023693112792199847L;
	
	public PartyCompany() {}
	
	public static PartyCompany getInstance(Long pkParty, Long pkCompany) {
		PartyCompany obj = new PartyCompany();
			obj.setParty(Party.getInstance(pkParty));
			obj.setCompany(Company.getInstance(pkCompany));
		return obj;
	}
	
	public static PartyCompany getInstance(Party party, Long pkCompany) {
		PartyCompany obj = new PartyCompany();
			obj.setParty(party);
			obj.setCompany(Company.getInstance(pkCompany));
		return obj;
	}
	
	public static PartyCompany getInstance(Long pkParty, Company company) {
		PartyCompany obj = new PartyCompany();
			obj.setParty(Party.getInstance(pkParty));
			obj.setCompany(company);
		return obj;
	}
	
	@Id
	@Column(name = "PK_PARTY_COMPANY", unique = true ,nullable = false)
	@SequenceGenerator(name="PARTY_COMPANY_PK_PARTY_COMPANY_SEQ", sequenceName="PARTY_COMPANY_PK_PARTY_COMPANY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PARTY_COMPANY_PK_PARTY_COMPANY_SEQ")
	private Long pkPartyCompany;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_PARTY")
	private Party party;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;

	public Long getPkPartyCompany() {
		return pkPartyCompany;
	}
	public void setPkPartyCompany(Long pkPartyCompany) {
		this.pkPartyCompany = pkPartyCompany;
	}

	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}