package id.base.app.valueobject.housingIndex;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "HOUSING_INDEX")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="housingIndexJid", scope=HousingIndex.class)
public class HousingIndex extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2410352630957149832L;
	
	public static final String PK_HOUSING_INDEX = "pkHousingIndex";
	public static final String PROVINCE = "province";
	public static final String VALUE = "value";
	
	public static HousingIndex getInstance() {
		return new HousingIndex();
	}
	
	@Id
	@SequenceGenerator(name="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ", sequenceName="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ")
	@Column(name = "PK_HOUSING_INDEX", unique = true ,nullable = false)
	private Long pkHousingIndex;
	
	@Column(name="PROVINCE")
	private String province;
	
	@Column(name="VALUE")
	private String value;

	public Long getPkHousingIndex() {
		return pkHousingIndex;
	}

	public void setPkHousingIndex(Long pkHousingIndex) {
		this.pkHousingIndex = pkHousingIndex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}