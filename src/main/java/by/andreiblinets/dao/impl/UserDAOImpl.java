package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements BaseDAO<User> {

    @Autowired
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public List<User> readAll() {
        return entityManager.createQuery(Query.GET_ALL_USERS).getResultList();
    }

    public User readById(Long id) {
        return entityManager.find(User.class,id);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }
}
