package by.andreiblinets.dao;

import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.News;

import java.util.List;

public interface NewsDAO extends BaseDAO<News>  {
    List<News> getNewsByIdCamelCase(long idCamelCase) throws DaoException;
}
