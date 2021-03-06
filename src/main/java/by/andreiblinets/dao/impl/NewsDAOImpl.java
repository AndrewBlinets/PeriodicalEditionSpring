package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.NewsDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.MyQuery;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.News;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsDAOImpl  extends BaseDAOImpl<News> implements NewsDAO {

    private static final String PARAMETER_PERODICAL_EDITION_ID = "id";
    private static Logger logger = Logger.getLogger(NewsDAOImpl.class.getName());

    public NewsDAOImpl() {
        super(News.class, logger);
    }

    @Override
    public List<News> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_NEWS).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_NEWS + e.getMessage());
            throw new DaoException(Error.ERROR_READ_NEWS + e.getMessage());
        }
    }

    @Override
    public List<News> getNewsByIdPeriodicalEdition(long id) throws DaoException {
        try {
            Query query = getEntityManager().createQuery(MyQuery.GET_NEWS_BY_ID_PREIODICAL_EDITION);
            query.setParameter(PARAMETER_PERODICAL_EDITION_ID,id);
            return query.getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_NEWS + e.getMessage());
            throw new DaoException(Error.ERROR_READ_NEWS + e.getMessage());
        }
    }
}
