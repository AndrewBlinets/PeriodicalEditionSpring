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
import by.andreiblinets.web.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
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
    public ModelAndView getAllNews(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        try {
            if (user.getUserRole().equals(String.valueOf(UserRole.EDITOR))) {
                try {
                    return pagePathManager.getPage(Parameters.NEWS,
                            newsService.getNewsByIdPeriodicalEdition(editorService.getPeriodicalEdition(user.getId())),
                            Page.PATH_EDITOR_NEWS);
                } catch (ServiceException e) {
                    return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
                }
            }
            if (user.getUserRole().equals(String.valueOf(UserRole.READER))) {
                try {
                    List<News> newsList = new ArrayList<>();
                    List<Subscription> subscriptions = subscriptionService.getSubscribtionByIdUser(user.getId());
                    for (Subscription sub : subscriptions) {
                        newsList.addAll(newsService.getNewsByIdPeriodicalEdition(sub.getPeriodicalEdition().getId()));
                    }
                    return pagePathManager.getPage(Parameters.NEWS, newsList, Page.PATH_READER_NEWS);
                } catch (ServiceException e) {
                    return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
                }
            } else {
                return pagePathManager.getPage(null, null, Page.CONTROL);
            }
        }
        catch (NullPointerException e)
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
    }

    @RequestMapping(value = "/addNewsPage", method = RequestMethod.GET)
    public ModelAndView getPageWithAddNews(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.EDITOR)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else
        {
            return pagePathManager.getPage(null, null, Page.ADD_NEWS);
        }
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public ModelAndView addNews(HttpServletRequest request, @ModelAttribute("news") News news) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.EDITOR)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            try {
                if(chekingIsNull(news)) {
                    String message = Validation.validationNews(news);
                    if(message != null)
                    {
                        return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, message, Page.ADD_NEWS);
                    }
                    news.setPeriodicalEdition(periodicalEditionService.readById(editorService.getPeriodicalEdition(user.getId())));
                    newsService.create(news);
                    return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.NEWS_CREATE_SUCSEC, Page.EDITOR_MAIN);
                }
                else
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.ERROR_FIELD_IS_NULL, Page.ADD_NEWS);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }

    // @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    @RequestMapping(value = "/news/remove/{id}")
    public ModelAndView delete(HttpServletRequest request, @PathVariable long id) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.EDITOR))) {
            try {
                newsService.delete(id);
                if (newsService.readById(id) == null) {
                    return pagePathManager.getPage(Parameters.NEWS, newsService.readAll(),
                            Page.PATH_EDITOR_NEWS);
                } else {
                    return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.NOT_DELETE_NEWS,
                            Page.PATH_EDITOR_NEWS);
                }
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
        else
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
    }

    @RequestMapping(value = "/newsupdate/{id}", method = RequestMethod.GET)
    public ModelAndView goUpdateNews(HttpServletRequest request, @PathVariable long id) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null)
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else
        {
            try {
                News news = newsService.readById(id);
                return pagePathManager.getPage(Parameters.NEWS, news, Page.UPDATE_NEWS);
            }
            catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }

    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public ModelAndView updateNews(HttpServletRequest request, @ModelAttribute News news) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null)
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else
        {
            try {
                newsService.update(news);
                return pagePathManager.getPage(Parameters.NEWS, newsService.readAll(), Page.ADD_NEWS);
            }
            catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }

    private boolean chekingIsNull(News news) {
        try {
            if (!news.getTitle().isEmpty()
                    & !news.getBody().isEmpty()
                    & !news.getAuthor().isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
}
