package id.base.app.valueobject.frontend;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "INTEGRATION_SCRIPT")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="integrationScriptJid", scope=IntegrationScript.class)
public class IntegrationScript extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6736020358709568915L;
	
	public static final String PK_INTEGRATION_SCRIPT = "pkIntegrationScript";
	public static final String TYPE = "type";
	public static final String URL = "url";
	public static final String SCRIPT = "script";
	public static final String STATUS = "status";
	
	public static IntegrationScript getInstance() {
		return new IntegrationScript();
	}
	
	@Id
	@SequenceGenerator(name="INTEGRATION_SCRIPT_PK_INTEGRATION_SCRIPT_SEQ", sequenceName="INTEGRATION_SCRIPT_PK_INTEGRATION_SCRIPT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INTEGRATION_SCRIPT_PK_INTEGRATION_SCRIPT_SEQ")
	@Column(name = "PK_INTEGRATION_SCRIPT", unique = true ,nullable = false)
	private Long pkIntegrationScript;
	
	@Column(name="TYPE")
	private Integer type;
	
	@Transient
	private String typeStr;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="POSITION")
	private Integer position;
	
	@Transient
	private String positionStr;
	
	@Column(name="SCRIPT")
	private String script;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;

	public Long getPkIntegrationScript() {
		return pkIntegrationScript;
	}

	public void setPkIntegrationScript(Long pkIntegrationScript) {
		this.pkIntegrationScript = pkIntegrationScript;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTypeStr() {
		return SystemConstant.IntegrationScriptType.TYPE_MAP.get(type);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		return ILookupConstant.Status.STATUS_MAP.get(status);
	}

	public String getPositionStr() {
		return SystemConstant.IntegrationScriptPosition.POSITION_MAP.get(position);
	}
	
}