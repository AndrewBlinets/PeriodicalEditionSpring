package by.andreiblinets.service.impl;

import by.andreiblinets.dao.SubscriptionDAO;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.service.SubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubscriptionServiceImpl extends BaseServiceImpl<Subscription> implements SubscriptionService {

    private static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO)
    {
        super(subscriptionDAO,logger);
    }
}
