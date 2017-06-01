package by.andreiblinets.dao;

import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Redactor;

import java.util.List;

public interface RedactorDAO extends BaseDAO<Redactor>  {
    List<Integer> getCamelCase(long id) throws DaoException;
}
