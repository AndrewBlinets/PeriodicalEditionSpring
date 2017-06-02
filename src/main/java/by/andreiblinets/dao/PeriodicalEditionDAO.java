package by.andreiblinets.dao;

import by.andreiblinets.entity.PeriodicalEdittion;
import by.andreiblinets.exceptions.DaoException;

public interface PeriodicalEditionDAO extends BaseDAO<PeriodicalEdittion>  {
    boolean chekingNameCamelCase(String name) throws DaoException;
}
