package by.andreiblinets.service.impl;

import by.andreiblinets.dao.SubscriptionDAO;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.service.SubscriptionService;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl extends BaseServiceImpl<Subscription> implements SubscriptionService {

    private static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    @Autowired
    private SubscriptionDAO subscriptionDAO;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO)
    {
        super(subscriptionDAO,logger);
    }

    @Override
    public List<Subscription> getSubscribtionByIdUser(long id) throws ServiceException {
        try {
            return subscriptionDAO.getSubscriptionByIdUser(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
