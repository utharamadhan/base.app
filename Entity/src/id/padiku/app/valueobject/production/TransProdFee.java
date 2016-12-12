package id.padiku.app.valueobject.production;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "TRANS_PROD_FEE")
public class TransProdFee implements Serializable {

	private static final long serialVersionUID = 6717260985498062809L;
	
	@Id
	@SequenceGenerator(name="TRANS_PROD_FEE_PK_TRANS_PROD_FEE_SEQ", sequenceName="TRANS_PROD_FEE_PK_TRANS_PROD_FEE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_PROD_FEE_PK_TRANS_PROD_FEE_SEQ")
	@Column(name = "PK_TRANS_PROD_FEE", unique = true ,nullable = false)
	private Long pkTransProdFee;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_PROD")
	private TransProd transProd;
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="FEE")
	private BigDecimal fee;

	public Long getPkTransProdFee() {
		return pkTransProdFee;
	}

	public void setPkTransProdFee(Long pkTransProdFee) {
		this.pkTransProdFee = pkTransProdFee;
	}

	public TransProd getTransProd() {
		return transProd;
	}

	public void setTransProd(TransProd transProd) {
		this.transProd = transProd;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

}
