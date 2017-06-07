package by.andreiblinets.dao;

import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Subscription;

import java.util.List;

public interface SubscriptionDAO extends BaseDAO<Subscription> {
    List<Subscription> getSubscriptionByIdUser(long id) throws DaoException;
    List<Subscription> getSubscriptionByIdPeriodicalEdition(long id) throws DaoException;
}
