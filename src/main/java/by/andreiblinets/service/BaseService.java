package by.andreiblinets.service;

import by.andreiblinets.service.exceptions.ServiceException;

import java.util.List;

public interface BaseService<T> {

    boolean create(T t) throws ServiceException;
    boolean update(T t);
    List<T> readAll();
    T readById(Long id);
    boolean delete(T t);

}
