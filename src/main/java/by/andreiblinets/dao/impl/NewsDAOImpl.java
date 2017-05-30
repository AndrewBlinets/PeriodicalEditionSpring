package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.NewsDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.News;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class NewsDAOImpl  extends BaseDAOImpl<News> implements NewsDAO {

    private static Logger logger = Logger.getLogger(NewsDAOImpl.class.getName());

    public NewsDAOImpl() {
        super(NewsDAOImpl.class, logger);
    }

    @Override
    public List<News> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_NEWS).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_NEWS + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_NEWS + e.getMessage());
        }
    }
}
