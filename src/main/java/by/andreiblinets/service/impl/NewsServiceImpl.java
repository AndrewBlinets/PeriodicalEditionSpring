package by.andreiblinets.service.impl;

import by.andreiblinets.dao.NewsDAO;
import by.andreiblinets.entity.News;
import by.andreiblinets.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

    private static Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO)
    {
        super(newsDAO,logger);
    }
}
