package id.padiku.app.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity(name="masterFee")
@Table(name = "MASTER_FEE")
public class MasterFee extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -8948760323323406007L;
	
	public static final String ID 			= "pkMasterFee";
	public static final String FEE_TYPE		= "feeType";
	public static final String FEE_TYPE_ID	= FEE_TYPE +"."+ Lookup.ID;
	public static final String FEE_TYPE_NAME_ID	= FEE_TYPE +"."+ Lookup.NAME_ID;
	public static final String DESCR		= "descr";
	public static final String UNIT_FEE		= "unitFee";
	public static final String UOM			= "uom";
	public static final String UOM_ID		= UOM +"."+ Lookup.ID;
	public static final String UOM_NAME_ID	= UOM +"."+ Lookup.NAME_ID;
	public static final String UOM_NAME_EN	= UOM +"."+ Lookup.NAME_EN;
	public static final String TOTAL_FEE	= "totalFee";
	public static final String STATUS		= "status";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, DESCR, UNIT_FEE, UOM_NAME_ID, UOM_NAME_EN, TOTAL_FEE, STATUS
	};
		
	@Id
	@SequenceGenerator(name="MASTER_FEE_PK_MASTER_FEE_SEQ", sequenceName="MASTER_FEE_PK_MASTER_FEE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MASTER_FEE_PK_MASTER_FEE_SEQ")
	@Column(name = "PK_MASTER_FEE", unique = true, nullable = false)
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkMasterFee;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_FEE_TYPE")
	private Lookup feeType;
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="UNIT_FEE")
	private BigDecimal unitFee;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_UOM")
	private Lookup uom;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkMasterFee() {
		return pkMasterFee;
	}

	public void setPkMasterFee(Long pkMasterFee) {
		this.pkMasterFee = pkMasterFee;
	}
	
	public Lookup getFeeType() {
		return feeType;
	}

	public void setFeeType(Lookup feeType) {
		this.feeType = feeType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getUnitFee() {
		return unitFee;
	}

	public void setUnitFee(BigDecimal unitFee) {
		this.unitFee = unitFee;
	}

	public Lookup getUom() {
		return uom;
	}

	public void setUom(Lookup uom) {
		this.uom = uom;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
