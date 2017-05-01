package id.base.app.dao.publication;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.publication.News;

import java.util.List;


public interface INewsDAO extends IBaseDAO<News> {

	public List<News> findLatestNews(int number) throws SystemException;

	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException;

	public News findByPermalink(String permalink) throws SystemException;

}