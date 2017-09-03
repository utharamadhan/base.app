package id.base.app.valueobject.frontend;

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
@Table(name = "SETTING")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="settingJid", scope=Setting.class)
public class Setting extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1034664739896623253L;

	public static final String PK_SETTING = "pkSetting";
	public static final String TYPE = "type";
	public static final String DATA_FROM = "dataFrom";
	public static final String LABEL1 = "label1";
	public static final String LABEL2 = "label2";
	public static final String VALUE = "value";
	public static final String ORDER_NO = "orderNo";
	public static final String STATUS = "status";
	
	@Id
	@SequenceGenerator(name="SETTING_PK_SETTING_SEQ", sequenceName="SETTING_PK_SETTING_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SETTING_PK_SETTING_SEQ")
	@Column(name = "PK_SETTING", unique = true ,nullable = false)
	private Long pkSetting;
	
	@Column(name="TYPE")
	private Integer type;
	
	@Column(name="DATA_FROM")
	private String dataFrom;
	
	@Column(name="LABEL1")
	private String label1;
	
	@Column(name="LABEL2")
	private String label2;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="STATUS")
	private Integer status;
	
	public Long getPkSetting() {
		return pkSetting;
	}

	public void setPkSetting(Long pkSetting) {
		this.pkSetting = pkSetting;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}