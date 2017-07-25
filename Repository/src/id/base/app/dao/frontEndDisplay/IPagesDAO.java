package id.base.app.dao.frontEndDisplay;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.Pages;

import java.util.List;

public interface IPagesDAO extends IBaseDAO<Pages> {

	public Pages findByPermalink(String permalink) throws SystemException;

	public List<Pages> findSimpleData(String type) throws SystemException;

	public List<Pages> getLatestPages(List<String> types) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public void updateThumb(Long pkPages, String thumbURL) throws SystemException;

}