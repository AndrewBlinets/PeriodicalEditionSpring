package by.andreiblinets.dao;

import java.util.List;

public interface BaseDAO<T> {

    void create(T t);
    void update(T t);
    List<T> readAll();
    T readById(Long id);
    void delete(T t);

}
