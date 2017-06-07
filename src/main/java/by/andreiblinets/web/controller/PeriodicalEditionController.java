package by.andreiblinets.web.controller;

import by.andreiblinets.entity.*;
import by.andreiblinets.entity.dto.PeriodicalEditionDTO;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.*;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import by.andreiblinets.web.util.Coding;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PeriodicalEditionController {

    @Autowired
    private PeriodicalEditionService periodicalEditionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private PagePathManager pagePathManager;

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(value = "/periodicalEditions", method = RequestMethod.GET)
    public ModelAndView getAllCamelCase(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            try {
                return pagePathManager.getPage(Parameters.PERIODICAL_EDITION_LIST, periodicalEditionService.readAll(),
                        Page.PATH_ADMIN_PERIODICAL_EDITION);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
        if(user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            try {
                return pagePathManager.getPage(Parameters.PERIODICAL_EDITION_LIST, periodicalEditionService.readAll(),
                        Page.READER_SHOW_PERIODICAL_EDITION_PAGE);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
        else
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
    }

    @RequestMapping(value = "/periodicalEdition", method = RequestMethod.POST)
    public ModelAndView createCamelCase(HttpServletRequest request, @ModelAttribute PeriodicalEditionDTO periodicalEditionDTO) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            try {
                if(periodicalEditionService.chekingNamePeriodicalEdition(periodicalEditionDTO.getName()))
                {
                    if(accountService.chekingLogin(periodicalEditionDTO.getLogin()))
                    {
                        Editor editor = new Editor();
                        Account account = new Account();
                        account.setLogin(periodicalEditionDTO.getLogin());
                        account.setHashpassword(Coding.md5Apache(periodicalEditionDTO.getPassword()));
                        User userEditor = new User();
                        userEditor.setAccount(account);
                        userEditor.setSurname(periodicalEditionDTO.getSurname());
                        userEditor.setName(periodicalEditionDTO.getName());
                        userEditor.setUserRole(String.valueOf(UserRole.REDACTOR));
                        PeriodicalEdition periodicalEdition = new PeriodicalEdition();
                        periodicalEdition.setPrice(Long.parseLong(periodicalEditionDTO.getPrice()));
                        periodicalEdition.setName(periodicalEditionDTO.getNamePeriodicalEdition());
                        editor.setPeriodicalEdition(periodicalEdition);
                        editor.setUser(userEditor);
                        editorService.create(editor);
                        return pagePathManager.getPage(Parameters.PERIODICAL_EDITION_LIST, periodicalEditionService.readAll(),
                                Page.PATH_ADMIN_PERIODICAL_EDITION);
                    }
                    else
                    {
                        return pagePathManager.getPage(Error.ERROR_EXISTENCE_PERIODICAL_EDITION, Message.ERROR_LOGIN_EXISTENCE,
                                Page.PATH_ADD_PERIODICAL_EDITION);
                    }
                }
                else
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_PERIODICAL_EDITION, Message.ERROR_PERIODICAL_EDITION_EXISTENCE,
                            Page.PATH_ADD_PERIODICAL_EDITION);
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

//    @RequestMapping(value = "/periodicalEdition/{id}", method = RequestMethod.PUT)
//    public String updateCamelCase(ModelMap model, @PathVariable("id") long id, @RequestBody PeriodicalEdition periodicalEdition) {
//       String pagePath = null;
//        try {
//            periodicalEditionService.update(periodicalEdition);
//            model.addAttribute(Parameters.CAMEL_CASE, periodicalEditionService.readById(id));
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }

   // @RequestMapping(value = "/periodicalEdition/{id}", method = RequestMethod.DELETE)
    @RequestMapping(value = "/periodicalEdition/remove/{id}")
    public ModelAndView delete(HttpServletRequest request, @PathVariable long id) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR))) {
            try {
                java.util.List<News> newsList =  newsService.getNewsByIdPeriodicalEdition(id);
                for (News news: newsList) {
                    newsService.delete(news.getId());
                }
                java.util.List<Subscription> subscriptions = subscriptionService.getSubscribtionByIdPeriodicalEdition(id);
                for (Subscription subscription: subscriptions) {
                    subscriptionService.delete(subscription.getId());
                }
                editorService.delete(id);
                if (editorService.readById(id) == null) {
                    return pagePathManager.getPage(Parameters.PERIODICAL_EDITION_LIST, periodicalEditionService.readAll(),
                            Page.PATH_ADMIN_PERIODICAL_EDITION);
                } else {
                    return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.NOT_DELETE_PERIODICAL_EDITION,
                            Page.PATH_ADMIN_PERIODICAL_EDITION);
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
}
