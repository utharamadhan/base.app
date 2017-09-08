package id.base.app.service.publication;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.publication.LinkUrl;

import java.util.List;

public interface ILinkUrlService extends MaintenanceService<LinkUrl> {

	public List<String> getSamePermalink(Long pk, String permalink)
			throws SystemException;

	public List<LinkUrl> findByPermalinkParent(String permalink) throws SystemException;

	public String getTitleByPermalink(String permalink) throws SystemException;

	public Long getPkByPermalink(String permalink) throws SystemException;


}
