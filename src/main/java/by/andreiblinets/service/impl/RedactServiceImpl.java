package by.andreiblinets.service.impl;

import by.andreiblinets.dao.RedactorDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Redactor;
import by.andreiblinets.service.RedactorService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RedactServiceImpl extends BaseServiceImpl<Redactor> implements RedactorService {

    private static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    @Autowired
    private RedactorDAO redactorDAO;

    @Autowired
    public RedactServiceImpl(RedactorDAO redactorDAO)
    {
        super(redactorDAO,logger);
    }

    @Override
    public Long getCamelCase(long id) throws ServiceException {
       try{
           List<Integer> idCamelCase = redactorDAO.getCamelCase(id);
           return Long.valueOf(idCamelCase.get(0));
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}

