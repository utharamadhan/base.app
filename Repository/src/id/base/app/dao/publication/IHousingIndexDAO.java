package id.base.app.dao.publication;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.List;

public interface IHousingIndexDAO extends IBaseDAO<HousingIndex> {

	public void updateLinkDetail(String linkUrl);

	public String getLinkUrlDetail() throws SystemException;

	public List<HousingIndex> findSimpleData() throws SystemException;

}
