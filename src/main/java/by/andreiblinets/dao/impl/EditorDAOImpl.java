package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.EditorDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.MyQuery;
import by.andreiblinets.entity.Editor;
import by.andreiblinets.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class EditorDAOImpl extends BaseDAOImpl<Editor> implements EditorDAO {

    private static final String PARAMETER_USER_ID = "id";
    private static Logger logger = Logger.getLogger(EditorDAOImpl.class.getName());

    public EditorDAOImpl() {
        super(Editor.class, logger);
    }

    @Override
    public List<Editor> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_REDACTOR).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_REDACTOR + e.getMessage());
            throw new DaoException(Error.ERROR_READ_REDACTOR + e.getMessage());
        }
    }

    @Override
    public List<Editor> getPeriodicalEdition(long id) throws DaoException {
        try {
            Query query= getEntityManager().createQuery(MyQuery.GET_EDITOR_BY_ID);
            query.setParameter(PARAMETER_USER_ID, id);
            return query.getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
            throw new DaoException(Error.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        }
    }
}
