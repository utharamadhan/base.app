package id.base.app.valueobject.publication;

import id.base.app.valueobject.Lookup;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="HOUSING_INDEX_PROVINCE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="housingIndexProvinceJid", scope=HousingIndexProvince.class)
public class HousingIndexProvince implements Serializable{

	private static final long serialVersionUID = -6905935674253519943L;

	@Id
	@SequenceGenerator(name="HOUSING_INDEX_PROVINCE_PK_HOUSING_INDEX_PROVINCE_SEQ", sequenceName="HOUSING_INDEX_PROVINCE_PK_HOUSING_INDEX_PROVINCE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HOUSING_INDEX_PROVINCE_PK_HOUSING_INDEX_PROVINCE_SEQ")
	@Column(name = "PK_HOUSING_INDEX_PROVINCE", unique = true ,nullable = false)
	private Long pkHousingIndexProvince;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_HOUSING_INDEX")
	private HousingIndex housingIndex;
	
	@OneToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name = "FK_LOOKUP_PROVINCE")
	private Lookup provinceLookup;
	
	@Column(name="INDEX_VALUE")
	private String indexValue;

	public Long getPkHousingIndexProvince() {
		return pkHousingIndexProvince;
	}

	public void setPkHousingIndexProvince(Long pkHousingIndexProvince) {
		this.pkHousingIndexProvince = pkHousingIndexProvince;
	}

	public HousingIndex getHousingIndex() {
		return housingIndex;
	}

	public void setHousingIndex(HousingIndex housingIndex) {
		this.housingIndex = housingIndex;
	}

	public Lookup getProvinceLookup() {
		return provinceLookup;
	}

	public void setProvinceLookup(Lookup provinceLookup) {
		this.provinceLookup = provinceLookup;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

}