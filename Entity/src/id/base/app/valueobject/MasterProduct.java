package id.base.app.valueobject;

import java.io.Serializable;

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

@Entity(name="masterProduct")
@Table(name = "MASTER_PRODUCT")
public class MasterProduct extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -8080122425069589377L;

	@Id
	@SequenceGenerator(name="MASTER_PRODUCT_PK_MASTER_PRODUCT_SEQ", sequenceName="MASTER_PRODUCT_PK_MASTER_PRODUCT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MASTER_PRODUCT_PK_MASTER_PRODUCT_SEQ")
	@Column(name = "PK_MASTER_PRODUCT", unique = true, nullable = false)
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkMasterProduct;
	
	@Column(name = "PRODUCT_NAME")
	private String name;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_UOM")
	private Lookup uom;
	
	@OneToOne
	@JoinColumn(name="FK_LOOKUP_ITEM_TYPE")
	private Lookup type;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkMasterProduct() {
		return pkMasterProduct;
	}

	public void setPkMasterProduct(Long pkMasterProduct) {
		this.pkMasterProduct = pkMasterProduct;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lookup getUom() {
		return uom;
	}

	public void setUom(Lookup uom) {
		this.uom = uom;
	}

	public Lookup getType() {
		return type;
	}

	public void setType(Lookup type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}