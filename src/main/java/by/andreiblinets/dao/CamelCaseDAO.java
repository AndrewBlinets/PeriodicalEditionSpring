package by.andreiblinets.dao;

import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.CamelCase;

public interface CamelCaseDAO  extends BaseDAO<CamelCase>  {
    boolean chekingNameCamelCase(String name) throws DaoException;
}
