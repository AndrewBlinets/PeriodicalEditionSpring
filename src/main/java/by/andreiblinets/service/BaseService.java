package by.andreiblinets.service;

import by.andreiblinets.service.exceptions.ServiceException;

import java.util.List;

public interface BaseService<T> {

    boolean create(T t) throws ServiceException;
    boolean update(T t) throws ServiceException;
    List<T> readAll() throws ServiceException;
    T readById(Long id) throws ServiceException;
    boolean delete(T t) throws ServiceException;

}
