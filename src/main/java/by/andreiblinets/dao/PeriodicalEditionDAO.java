package by.andreiblinets.dao;

import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.exceptions.DaoException;

public interface PeriodicalEditionDAO extends BaseDAO<PeriodicalEdition>  {
    boolean chekingNamePeriodicalEdition(String name) throws DaoException;
}
