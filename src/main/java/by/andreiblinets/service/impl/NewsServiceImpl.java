package by.andreiblinets.service.impl;

import by.andreiblinets.dao.NewsDAO;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.News;
import by.andreiblinets.service.NewsService;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

    private static Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());
    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO)
    {
        super(newsDAO,logger);
    }

    @Override
    public List<News> getNewsByIdPeriodicalEdition(long idCamelCase) throws ServiceException {
        try {
            return newsDAO.getNewsByIdPeriodicalEdition(idCamelCase);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
