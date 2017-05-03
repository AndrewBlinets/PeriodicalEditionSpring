package by.andreiblinets.dao;

import by.andreiblinets.dao.exceptions.DaoException;

import java.util.List;

public interface BaseDAO<T> {
    void create(T t) throws DaoException;
    void update(T t) throws DaoException;
    List<T> readAll() throws DaoException;
    T readById(Long id) throws DaoException;
    void delete(T t) throws DaoException;
}
