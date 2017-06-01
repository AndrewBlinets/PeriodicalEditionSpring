package by.andreiblinets.web.controller;

import by.andreiblinets.entity.News;
import by.andreiblinets.entity.Redactor;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.*;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Message;
import by.andreiblinets.web.constant.Page;
import by.andreiblinets.web.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import by.andreiblinets.web.constant.Error;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private RedactorService redactorService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CamelCaseService camelCaseService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getAllAccount(ModelMap model, HttpServletRequest request) {
        String pagePath = null;
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.REDACTOR)))
        {
            try {
                long idCamelCase = redactorService.getCamelCase(user.getId());
                List<News> newsList = newsService.getNewsByIdCamelCase(idCamelCase);
                 model.addAttribute(Parameters.NEWS, newsList);
                pagePath = pagePathManager.getProperty(Page.REDACTOR_NEWS);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
        if(user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            try {
                List<News> newsList = new ArrayList<>();
                List<Subscription> subscriptions = subscriptionService.getSubscribtionByIdUser(user.getId());
                for (Subscription sub :subscriptions) {
                    newsList.addAll(newsService.getNewsByIdCamelCase(sub.getCamelCase().getId()));
                }
                model.addAttribute(Parameters.NEWS, newsList);
                pagePath = pagePathManager.getProperty(Page.REDADER_NEWS);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
        else
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
    }

    @RequestMapping(value = "/addNewsPage", method = RequestMethod.GET)
    public String getPageWithAddNews(ModelMap model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.REDACTOR)))
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
        else {
            return pagePathManager.getProperty(Page.ADD_NEWS);
        }
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(ModelMap model, HttpServletRequest request, @ModelAttribute("news") News news) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.REDACTOR)))
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
        else {
            String pagePath = null;
            try {
                news.setCamelCase(camelCaseService.readById(redactorService.getCamelCase(user.getId())));
                newsService.create(news);
                model.addAttribute(Parameters.OPERATION_MESSAGE,Message.NEWS_CREATE_SUCSEC);
                pagePath = pagePathManager.getProperty(Page.REDACTOR_MAIN);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
    }
}
