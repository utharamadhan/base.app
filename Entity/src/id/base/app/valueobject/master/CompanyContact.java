package id.base.app.valueobject.master;

import id.base.app.valueobject.CreateEntity;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.UpdateEntity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "COMPANY_CONTACT")
public class CompanyContact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9093486103055112059L;

	@Id
	@SequenceGenerator(name="COMPANY_CONTACT_PK_COMPANY_CONTACT_SEQ", sequenceName="COMPANY_CONTACT_PK_COMPANY_CONTACT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_CONTACT_PK_COMPANY_CONTACT_SEQ")
	@Column(name = "PK_COMPANY_CONTACT", unique = true, nullable = false)
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkCompanyContact;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_CONTACT_TYPE")
	private Lookup contactType;
	
	@Column(name="CONTACT")
	private String contact;

	public Long getPkCompanyContact() {
		return pkCompanyContact;
	}
	public void setPkCompanyContact(Long pkCompanyContact) {
		this.pkCompanyContact = pkCompanyContact;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public Lookup getContactType() {
		return contactType;
	}
	public void setContactType(Lookup contactType) {
		this.contactType = contactType;
	}

	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}