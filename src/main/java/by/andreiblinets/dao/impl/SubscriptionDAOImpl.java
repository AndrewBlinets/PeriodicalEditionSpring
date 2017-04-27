package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SubscriptionDAOImpl implements BaseDAO<Subscription> {

    @Autowired
    private EntityManager entityManager;

    public void create(Subscription subscription) {
        entityManager.persist(subscription);
    }

    public void update(Subscription subscription) {
        entityManager.merge(subscription);
    }

    public List<Subscription> readAll() {
        return entityManager.createQuery(Query.GET_ALL_SUBSCRIPTION).getResultList();
    }

    public Subscription readById(Long id) {
        return entityManager.find(Subscription.class,id);
    }

    public void delete(Subscription subscription) {
        entityManager.remove(subscription);
    }
}
