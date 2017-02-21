package id.base.app.dao.news;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.news.News;

import java.util.List;


public interface INewsDAO extends IBaseDAO<News> {

	public List<News> findLatestNews(int number) throws SystemException;

}