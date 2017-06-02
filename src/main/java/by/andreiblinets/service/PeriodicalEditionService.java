package by.andreiblinets.service;

import by.andreiblinets.entity.PeriodicalEdittion;
import by.andreiblinets.exceptions.ServiceException;

public interface PeriodicalEditionService extends BaseService<PeriodicalEdittion> {
    boolean chekingNameCamelCase(String name) throws ServiceException;
}
