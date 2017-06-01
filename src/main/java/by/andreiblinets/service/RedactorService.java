package by.andreiblinets.service;

import by.andreiblinets.entity.Redactor;
import by.andreiblinets.service.exceptions.ServiceException;

public interface RedactorService extends BaseService<Redactor> {
    Long getCamelCase(long id) throws ServiceException;
}
