package id.base.app.valueobject.procurement;

import id.base.app.valueobject.CreateEntity;
import id.base.app.valueobject.UpdateEntity;

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
@Table(name = "TRANS_IN_FEE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="transInFeeJid", scope=TransInFee.class)
public class TransInFee implements Serializable {

	private static final long serialVersionUID = -2618378890908259333L;

	public static final String ID = "pkTransInFee";
	public static final String DESCR = "descr";
	public static final String FEE = "fee";
	
	@Id
	@Column(name = "PK_TRANS_IN_FEE", unique = true ,nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name="TRANS_IN_FEE_PK_TRANS_IN_FEE_SEQ", sequenceName="TRANS_IN_FEE_PK_TRANS_IN_FEE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANS_IN_FEE_PK_TRANS_IN_FEE_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkTransInFee;

	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_IN", nullable=true)
	private TransIn transIn = new TransIn();
	
	@Column(name="DESCR")
	private String descr;
	
	@Column(name="FEE")
	private BigDecimal fee;

	public Long getPkTransInFee() {
		return pkTransInFee;
	}

	public void setPkTransInFee(Long pkTransInFee) {
		this.pkTransInFee = pkTransInFee;
	}

	public TransIn getTransIn() {
		return transIn;
	}

	public void setTransIn(TransIn transIn) {
		this.transIn = transIn;
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
