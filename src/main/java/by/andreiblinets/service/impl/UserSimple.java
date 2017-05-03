package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.impl.PaymentDAOImpl;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.BaseService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSimple implements BaseService<User> {

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Autowired
    private BaseDAO<User> userBaseDAO;

    @Override
    public boolean create(User user) {
        try {
            userBaseDAO.create(user);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            userBaseDAO.update(user);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> readAll() {
        return userBaseDAO.readAll();
    }

    @Override
    public User readById(Long id) {
        return userBaseDAO.readById(id);
    }

    @Override
    public boolean delete(User user) {
        try {
            userBaseDAO.delete(user);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }
}
