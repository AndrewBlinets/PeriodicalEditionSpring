package by.andreiblinets.service.impl;

import by.andreiblinets.dao.UserDAO;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public UserServiceImpl (UserDAO userDAO)
    {
        super(userDAO,logger);
    }
}
