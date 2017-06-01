package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.SubscriptionDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Subscription;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class SubscriptionDAOImpl extends BaseDAOImpl<Subscription> implements SubscriptionDAO {

    private static Logger logger = Logger.getLogger(SubscriptionDAOImpl.class.getName());

    public SubscriptionDAOImpl() {
        super(Subscription.class, logger);
    }

    @Override
    public List<Subscription> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_SUBSCRIPTION).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_SUBSCRIPTION + e.getMessage());
        }
    }

    @Override
    public List<Subscription> getSubscriptionByIdUser(long idUser) throws DaoException {
        try {
            Query query = getEntityManager().createQuery(MyQuery.GET_SUBSCRIPTION_BY_ID_USER);
            query.setParameter("idUser",idUser);
            return query.getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_NEWS + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_NEWS + e.getMessage());
        }
    }
}
