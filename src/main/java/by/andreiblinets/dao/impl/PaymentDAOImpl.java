package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.PaymentDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.MyQuery;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Payment;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDAOImpl extends BaseDAOImpl<Payment> implements PaymentDAO {

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    public PaymentDAOImpl() {
        super(Payment.class, logger);
    }

    @Override
    public List<Payment> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_PAYMENT).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_PAYMENT + e.getMessage());
            throw new DaoException(Error.ERROR_READ_PAYMENT + e.getMessage());
        }
    }
}
