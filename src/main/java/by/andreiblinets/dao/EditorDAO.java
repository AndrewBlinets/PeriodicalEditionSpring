package by.andreiblinets.dao;

import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Editor;

import java.util.List;

public interface EditorDAO extends BaseDAO<Editor>  {
    List<Integer> getCamelCase(long id) throws DaoException;
}
