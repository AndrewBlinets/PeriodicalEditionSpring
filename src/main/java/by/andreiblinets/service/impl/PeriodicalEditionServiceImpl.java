package by.andreiblinets.service.impl;

import by.andreiblinets.dao.PeriodicalEditionDAO;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.service.PeriodicalEditionService;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodicalEditionServiceImpl extends BaseServiceImpl<PeriodicalEdition> implements PeriodicalEditionService {

    private static Logger logger = Logger.getLogger(PeriodicalEditionServiceImpl.class.getName());

    @Autowired
    private PeriodicalEditionDAO periodicalEditionDAO;

    @Autowired
    public PeriodicalEditionServiceImpl(PeriodicalEditionDAO periodicalEditionDAO) {
        super(periodicalEditionDAO, logger);
    }

    @Override
    public boolean chekingNamePeriodicalEdition(String name) throws ServiceException {
        try {
            return periodicalEditionDAO.chekingNameCamelCase(name);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
