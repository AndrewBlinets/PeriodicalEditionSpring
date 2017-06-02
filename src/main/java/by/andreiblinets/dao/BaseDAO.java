package by.andreiblinets.dao;

import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.AbstractEntity;

import java.util.List;

public interface BaseDAO<T extends AbstractEntity> {
    void create(T t) throws DaoException;
    void update(T t) throws DaoException;
    List<T> readAll() throws DaoException;
    T readById(Long id) throws DaoException;
    void delete(T t) throws DaoException;
}
