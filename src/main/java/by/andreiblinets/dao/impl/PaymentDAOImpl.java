package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PaymentDAOImpl implements BaseDAO<Payment> {

    @Autowired
    private EntityManager entityManager;

    public void create(Payment payment) {
        entityManager.persist(payment);
    }

    public void update(Payment payment) {
        entityManager.merge(payment);
    }

    public List<Payment> readAll() {
        return entityManager.createQuery(Query.GET_ALL_PAYMENT).getResultList();
    }

    public Payment readById(Long id) {
        return entityManager.find(Payment.class, id);
    }

    public void delete(Payment payment) {
        entityManager.remove(payment);
    }
}
