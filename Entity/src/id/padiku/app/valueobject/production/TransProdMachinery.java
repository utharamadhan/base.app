package id.padiku.app.valueobject.production;

import id.padiku.app.valueobject.master.CompanyMachinery;

import java.io.Serializable;

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
@Table(name = "TRANS_PROD_MACHINERY")
public class TransProdMachinery implements Serializable {

	private static final long serialVersionUID = -7049338436655313650L;
	
	@Id
	@SequenceGenerator(name="TRANS_PROD_MACHINERY_PK_TRANS_PROD_MACHINERY_SEQ", sequenceName="TRANS_PROD_MACHINERY_PK_TRANS_PROD_MACHINERY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_PROD_MACHINERY_PK_TRANS_PROD_MACHINERY_SEQ")
	@Column(name = "PK_TRANS_PROD_MACHINERY", unique = true ,nullable = false)
	private Long pkTransProdMachinery;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_PROD")
	private TransProd transProd;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY_MACHINERY")
	private CompanyMachinery companyMachinery;
	
	@Column(name="ORDER_NO")
	private Integer OrderNo;

	public Long getPkTransProdMachinery() {
		return pkTransProdMachinery;
	}

	public void setPkTransProdMachinery(Long pkTransProdMachinery) {
		this.pkTransProdMachinery = pkTransProdMachinery;
	}

	public TransProd getTransProd() {
		return transProd;
	}

	public void setTransProd(TransProd transProd) {
		this.transProd = transProd;
	}

	public CompanyMachinery getCompanyMachinery() {
		return companyMachinery;
	}

	public void setCompanyMachinery(CompanyMachinery companyMachinery) {
		this.companyMachinery = companyMachinery;
	}

	public Integer getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(Integer orderNo) {
		OrderNo = orderNo;
	}
}
