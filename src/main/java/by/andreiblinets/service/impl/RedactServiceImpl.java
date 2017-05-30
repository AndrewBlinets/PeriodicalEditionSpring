package by.andreiblinets.service.impl;

import by.andreiblinets.dao.RedactorDAO;
import by.andreiblinets.entity.Redactor;
import by.andreiblinets.service.RedactorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RedactServiceImpl extends BaseServiceImpl<Redactor> implements RedactorService {

    private static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    @Autowired
    public RedactServiceImpl(RedactorDAO redactorDAO)
    {
        super(redactorDAO,logger);
    }
}

