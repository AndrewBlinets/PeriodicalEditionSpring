package by.andreiblinets.service;

import by.andreiblinets.entity.User;
import by.andreiblinets.exceptions.ServiceException;

import java.util.List;

public interface UserService extends BaseService<User> {
    List<User> readReader() throws ServiceException;
}
