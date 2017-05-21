package by.andreiblinets.service.impl;

import by.andreiblinets.dao.CamelCaseDAO;
import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.service.CamelCaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CamelCaseServiceImpl extends BaseServiceImpl<CamelCase> implements CamelCaseService {

    private static Logger logger = Logger.getLogger(CamelCaseServiceImpl.class.getName());

    @Autowired
    private CamelCaseDAO camelCaseDAO;

    @Autowired
    public CamelCaseServiceImpl(CamelCaseDAO camelCaseDAO) {
        super(camelCaseDAO, logger);
    }
}
