package id.base.app.valueobject.master;

import id.base.app.valueobject.procurement.TransInItem;
import id.base.app.valueobject.production.TransProd;
import id.base.app.valueobject.sales.TransOut;

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
@Table(name = "STOCK_HISTORY")
public class StockHistory implements Serializable {

	private static final long serialVersionUID = 2247059729112484936L;
	
	@Id
	@SequenceGenerator(name="STOCK_HISTORY_PK_STOCK_HISTORY_SEQ", sequenceName="STOCK_HISTORY_PK_STOCK_HISTORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STOCK_HISTORY_PK_STOCK_HISTORY_SEQ")
	@Column(name = "PK_STOCK_HISTORY", unique = true ,nullable = false)
	private Long pkStockHistory;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_STOCK")
	private Stock stock;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_IN_ITEM")
	private TransInItem transInItem;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_PROD")
	private TransProd transProd;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_TRANS_OUT_ITEM")
	private TransOut transOutItem;

	public Long getPkStockHistory() {
		return pkStockHistory;
	}

	public void setPkStockHistory(Long pkStockHistory) {
		this.pkStockHistory = pkStockHistory;
	}
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public TransInItem getTransInItem() {
		return transInItem;
	}

	public void setTransInItem(TransInItem transInItem) {
		this.transInItem = transInItem;
	}

	public TransProd getTransProd() {
		return transProd;
	}

	public void setTransProd(TransProd transProd) {
		this.transProd = transProd;
	}

	public TransOut getTransOutItem() {
		return transOutItem;
	}

	public void setTransOutItem(TransOut transOutItem) {
		this.transOutItem = transOutItem;
	}

}
