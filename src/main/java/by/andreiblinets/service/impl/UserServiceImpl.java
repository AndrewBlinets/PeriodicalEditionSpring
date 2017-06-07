package by.andreiblinets.service.impl;

import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.dao.UserDAO;
import by.andreiblinets.entity.User;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl (UserDAO userDAO)
    {
        super(userDAO,logger);
    }

    @Override
    public List<User> readReader() throws ServiceException {
        try {
            return userDAO.readReader();
        }
         catch (DaoException e) {
        logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
    }

    }
}
