package by.andreiblinets.service.impl;

import by.andreiblinets.dao.EditorDAO;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Editor;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.service.EditorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EditorServiceImpl extends BaseServiceImpl<Editor> implements EditorService {

    private static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    @Autowired
    private EditorDAO editorDAO;

    @Autowired
    public EditorServiceImpl(EditorDAO editorDAO)
    {
        super(editorDAO,logger);
    }

    @Override
    public Long getPeriodicalEdition(long id) throws ServiceException {
       try{
           return Long.valueOf(editorDAO.getPeriodicalEdition(id).get(0).getPeriodicalEdition().getId());
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}

