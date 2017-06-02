package by.andreiblinets.service;

import by.andreiblinets.entity.Editor;
import by.andreiblinets.exceptions.ServiceException;

public interface EditorService extends BaseService<Editor> {
    Long getCamelCase(long id) throws ServiceException;
}
