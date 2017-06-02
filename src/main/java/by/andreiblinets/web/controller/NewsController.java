package by.andreiblinets.web.controller;

import by.andreiblinets.entity.News;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.PeriodicalEditionService;
import by.andreiblinets.service.NewsService;
import by.andreiblinets.service.EditorService;
import by.andreiblinets.service.SubscriptionService;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PeriodicalEditionService periodicalEditionService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getAllAccount(ModelMap model, HttpServletRequest request) {
        String pagePath = null;
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.REDACTOR)))
        {
            try {
                long idCamelCase = editorService.getCamelCase(user.getId());
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
                    newsList.addAll(newsService.getNewsByIdCamelCase(sub.getPeriodicalEdittion().getId()));
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
                news.setPeriodicalEdittion(periodicalEditionService.readById(editorService.getCamelCase(user.getId())));
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
