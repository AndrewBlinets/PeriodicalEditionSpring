package by.andreiblinets.service;

import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.service.exceptions.ServiceException;

public interface CamelCaseService extends BaseService<CamelCase> {
    boolean chekingNameCamelCase(String name) throws ServiceException;
}
