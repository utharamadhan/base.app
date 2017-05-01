package id.base.app.service.news;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.publication.News;

import java.util.List;

public interface INewsService extends MaintenanceService<News> {

	public List<News> findLatestNews(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public News findByPermalink(String permalink) throws SystemException;

}
