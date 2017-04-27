package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.entity.PeriodicalEdition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PeriodicalEditionDAOImpl implements BaseDAO<PeriodicalEdition> {

    @Autowired
    private EntityManager entityManager;

    public void create(PeriodicalEdition periodicalEdition) {
        entityManager.persist(periodicalEdition);
    }

    public void update(PeriodicalEdition periodicalEdition) {
        entityManager.merge(periodicalEdition);
    }

    public List<PeriodicalEdition> readAll() {
        return entityManager.createQuery(Query.GET_ALL_PERIODICAL_EDITION).getResultList();
    }

    public PeriodicalEdition readById(Long id) {
        return entityManager.find(PeriodicalEdition.class, id);
    }

    public void delete(PeriodicalEdition periodicalEdition) {
        entityManager.remove(periodicalEdition);
    }
}
