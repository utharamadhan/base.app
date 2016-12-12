package id.padiku.app.valueobject.sales;

import id.padiku.app.valueobject.CreateEntity;
import id.padiku.app.valueobject.UpdateEntity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TRANS_OUT_FEE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transOutFeeJid", scope=TransOutFee.class)
public class TransOutFee implements Serializable {

	private static final long serialVersionUID = -3204383725039298952L;
	
	@Id
	@Column(name = "PK_TRANS_OUT_FEE", unique = true ,nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name="TRANS_OUT_FEE_PK_TRANS_OUT_FEE_SEQ", sequenceName="TRANS_OUT_FEE_PK_TRANS_OUT_FEE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_OUT_FEE_PK_TRANS_OUT_FEE_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkTransOutFee;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_OUT", nullable=true)
	private TransOut transOut = new TransOut();
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="FEE")
	private BigDecimal fee;

	public Long getPkTransOutFee() {
		return pkTransOutFee;
	}

	public void setPkTransOutFee(Long pkTransOutFee) {
		this.pkTransOutFee = pkTransOutFee;
	}

	public TransOut getTransOut() {
		return transOut;
	}

	public void setTransOut(TransOut transOut) {
		this.transOut = transOut;
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
