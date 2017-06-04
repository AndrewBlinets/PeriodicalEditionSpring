package by.andreiblinets.service;

import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.exceptions.ServiceException;

public interface PeriodicalEditionService extends BaseService<PeriodicalEdition> {
    boolean chekingNameCamelCase(String name) throws ServiceException;
}
