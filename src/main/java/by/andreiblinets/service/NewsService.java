package by.andreiblinets.service;

import by.andreiblinets.entity.News;
import by.andreiblinets.exceptions.ServiceException;

import java.util.List;

public interface NewsService extends BaseService<News> {
    List<News> getNewsByIdPeriodicalEdition(long idCamelCase) throws ServiceException;
}
