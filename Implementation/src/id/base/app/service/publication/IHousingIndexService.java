package id.base.app.service.publication;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.List;

public interface IHousingIndexService extends MaintenanceService<HousingIndex> {

	public void updateLinkDetail(String linkUrl) throws SystemException;

	public String getLinkUrlDetail() throws SystemException;

	public List<HousingIndex> findSimpleData() throws SystemException;

}
