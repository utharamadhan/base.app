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

@Entity(name="masterMachinery")
@Table(name = "MASTER_MACHINERY")
public class MasterMachinery extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4771532209559384715L;
	
	@Id
	@SequenceGenerator(name="MASTER_MACHINERY_PK_MASTER_MACHINERY_SEQ", sequenceName="MASTER_MACHINERY_PK_MASTER_MACHINERY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MASTER_MACHINERY_PK_MASTER_MACHINERY_SEQ")
	@Column(name = "PK_MASTER_MACHINERY", unique = true, nullable = false)
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkMasterMachinery;
	
	@Column(name="MACHINE_NAME")
	private String name;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_MODEL")
	private Lookup model;
	
	@Column(name="MACHINE_CAPACITY")
	private BigDecimal capacity;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_WEIGHTING")
	private Lookup weighting;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_CAPACITY_UOM")
	private Lookup capacityUOM;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_MACHINE_POWER_SOURCE")
	private Lookup powerSource;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkMasterMachinery() {
		return pkMasterMachinery;
	}

	public void setPkMasterMachinery(Long pkMasterMachinery) {
		this.pkMasterMachinery = pkMasterMachinery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lookup getModel() {
		return model;
	}

	public void setModel(Lookup model) {
		this.model = model;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public Lookup getWeighting() {
		return weighting;
	}

	public void setWeighting(Lookup weighting) {
		this.weighting = weighting;
	}

	public Lookup getCapacityUOM() {
		return capacityUOM;
	}

	public void setCapacityUOM(Lookup capacityUOM) {
		this.capacityUOM = capacityUOM;
	}

	public Lookup getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(Lookup powerSource) {
		this.powerSource = powerSource;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
