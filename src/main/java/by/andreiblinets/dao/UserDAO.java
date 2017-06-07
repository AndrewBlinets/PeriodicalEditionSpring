package by.andreiblinets.dao;

import by.andreiblinets.entity.User;
import by.andreiblinets.exceptions.DaoException;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {
    List<User> readReader() throws DaoException;
}
