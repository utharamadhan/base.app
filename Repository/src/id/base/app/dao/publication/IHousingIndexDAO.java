package id.base.app.dao.publication;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.publication.HousingIndex;

public interface IHousingIndexDAO extends IBaseDAO<HousingIndex> {

	public void updateLinkDetail(String linkUrl);

	public String getLinkUrlDetail() throws SystemException;

}
