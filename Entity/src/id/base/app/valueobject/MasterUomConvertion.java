package id.base.app.valueobject;

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

@Entity
@Table(name = "MASTER_UOM_CONVERTION")
public class MasterUomConvertion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7750023413770563991L;
	
	@Id
	@Column(name = "PK_MASTER_UOM_CONVERTION", unique = true, nullable = false)
	@SequenceGenerator(name="MASTER_UOM_CONVERTION_PK_MASTER_UOM_CONVERTION_SEQ", sequenceName="MASTER_UOM_CONVERTION_PK_MASTER_UOM_CONVERTION_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MASTER_UOM_CONVERTION_PK_MASTER_UOM_CONVERTION_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkMasterUomConvertion;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_UOM_FROM")
	private Lookup uomFrom;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_UOM_TO")
	private Lookup uomTo;
	
	@Column(name="UOM_VALUE")
	private BigDecimal uomValue;

	public Long getPkMasterUomConvertion() {
		return pkMasterUomConvertion;
	}

	public void setPkMasterUomConvertion(Long pkMasterUomConvertion) {
		this.pkMasterUomConvertion = pkMasterUomConvertion;
	}

	public Lookup getUomFrom() {
		return uomFrom;
	}

	public void setUomFrom(Lookup uomFrom) {
		this.uomFrom = uomFrom;
	}

	public Lookup getUomTo() {
		return uomTo;
	}

	public void setUomTo(Lookup uomTo) {
		this.uomTo = uomTo;
	}

	public BigDecimal getUomValue() {
		return uomValue;
	}

	public void setUomValue(BigDecimal uomValue) {
		this.uomValue = uomValue;
	}
}
