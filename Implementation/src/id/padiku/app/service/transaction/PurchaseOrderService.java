package id.padiku.app.service.transaction;

import id.padiku.app.dao.lookup.ILookupDAO;
import id.padiku.app.dao.master.ICompanyDAO;
import id.padiku.app.dao.party.IPartyDAO;
import id.padiku.app.dao.transaction.IPurchaseOrderDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseOrderService implements IPurchaseOrderService{

	@Autowired
	private IPurchaseOrderDAO poDAO;
	@Autowired
	private ICompanyDAO rmuDAO;
	@Autowired
	private ILookupDAO lookupDAO;
	@Autowired
	private IPartyDAO partyDAO;
	
	@Override
	public PurchaseOrder findById(Long id) throws SystemException {
		return poDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(PurchaseOrder anObject) throws SystemException {
		poDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		poDAO.delete(objectPKs);
	}

	@Override
	public List<PurchaseOrder> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<PurchaseOrder> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return poDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<PurchaseOrder> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException {
		return poDAO.getPurchaseOrderById(maintenancePK);
	}

	@Override
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException {
		return poDAO.findAllByStock(pkStock);
	}

	/* -- old logic saving buyTransaction
	 * if(anObject.getPartySeller()!=null && anObject.getPartySeller().getPkParty()==null){
			Set<PartyRole> prSet = new HashSet<PartyRole>();
			PartyRole pr = new PartyRole();
			pr.setRole(lookupDAO.findByCode(ILookupConstant.PartyRole.SELLER, ILookupGroupConstant.PARTY_ROLE));
			pr.setParty(anObject.getPartySeller());
			prSet.add(pr);
			anObject.getPartySeller().setPartyRoles(prSet);
			partyDAO.saveOrUpdate(anObject.getPartySeller());
		}
		if(anObject.getStock() != null){
			anObject.getStock().setRmu(anObject.getRmu());
			anObject.getStock().setRmuStockLocation(rmuDAO.findLocationByRmu(anObject.getRmu().getPkRmu()));
			anObject.getStock().setStockName(lookupDAO.findById(anObject.getStock().getStockType().getPkLookup()).getName());
			anObject.setVolume(anObject.getStock().getVolume());
			anObject.setVolumeUnit(anObject.getStock().getVolumeUnit());
			//count totalBuyFee
			BigDecimal totalBuyFee = new BigDecimal(0);
			MathContext mc = new MathContext(SystemConstant.CurrencyDataType.PRECISION); 
			totalBuyFee = anObject.getUnitBuyFee().multiply(new BigDecimal(anObject.getStock().getVolume()), mc);
			if(anObject.getTransBuyOtherFees() != null){
				for(TransBuyOtherFee item : anObject.getTransBuyOtherFees()){
					totalBuyFee = totalBuyFee.add(item.getFee());
				}
			}
			anObject.setTotalBuyFee(totalBuyFee);
			anObject.getStock().setHpp(totalBuyFee.divide(new BigDecimal(anObject.getStock().getVolume()),SystemConstant.CurrencyDataType.SCALE,RoundingMode.HALF_UP));
	
			Stock stockCombine = transBuyDAO.getPkStockForCombine(anObject.getPartySeller().getPkParty(), anObject.getBuyingDate(), anObject.getStock().getStockType().getPkLookup());
			if(stockCombine!=null && stockCombine.getPkStock()!=null && stockCombine.getPkStock()>0L){
				BigDecimal totalFeeCombine = stockCombine.getHpp().multiply(new BigDecimal(stockCombine.getVolume()));
				BigDecimal totalFee = anObject.getStock().getHpp().multiply(new BigDecimal(anObject.getStock().getVolume()));
				BigDecimal totalFeeResult = totalFeeCombine.add(totalFee);
				Long totalVolume = stockCombine.getVolume() + anObject.getStock().getVolume();
				anObject.getStock().setPkStock(stockCombine.getPkStock());
				anObject.getStock().setVolume(totalVolume);
				anObject.getStock().setHpp(totalFeeResult.divide(new BigDecimal(totalVolume),SystemConstant.CurrencyDataType.SCALE,RoundingMode.HALF_UP));
			}
			stockDAO.saveOrUpdate(anObject.getStock());
		}
		if(anObject.getTransBuyOtherFees() != null){
			for(TransBuyOtherFee item : anObject.getTransBuyOtherFees()){
				item.setTransBuy(anObject);
			}
		}
	 */
}