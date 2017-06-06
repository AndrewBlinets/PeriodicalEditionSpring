package by.andreiblinets.web.controller;

import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.UserService;
import by.andreiblinets.web.mamager.PagePathManager;
import by.andreiblinets.web.util.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView aitification(HttpServletRequest request, @ModelAttribute("registration") Account account) {
        HttpSession httpSession  = request.getSession();
        User user = (User) httpSession.getAttribute(Parameters.USER);
        if(user != null)
        {
            return backPage(user);
        }
        else {
            if(chekingField(account)) {
                try {
                    return chekingUser(account, httpSession);
                } catch (ServiceException e) {
                    return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
                }
            }
            else {
                return pagePathManager.getPage(Parameters.EROR_LOGIN_OR_PASSWORD, Message.EMPYTY_FIELD,
                        Page.INDEX);
            }
        }
    }

    private boolean chekingField(Account account) {
        try{
            if (!account.getLogin().isEmpty()
                    & !account.getHashpassword().isEmpty())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }

    private ModelAndView backPage(User user) {
        switch (user.getUserRole())
        {
            case "ADMINISTRATOR":
            {
                return pagePathManager.getPage(Parameters.USER, user, Page.ADMIN_MAIN);
            }
            case "READER":
            {
                return pagePathManager.getPage(Parameters.USER, user, Page.READER_MAIN);
            }
            case "REDACTOR":
            {
                return pagePathManager.getPage(Parameters.USER, user, Page.PATH_EDITOR_MAIN);
            }
            default:
            {
                return pagePathManager.getPage(Parameters.EROR_LOGIN_OR_PASSWORD,
                        Message.ERROR_USER_LOGIN_OR_PASSWORD, Page.INDEX);
            }
        }
    }

    private ModelAndView chekingUser(Account account, HttpSession httpSession) throws ServiceException {
        User user = accountService.getUser(account.getLogin(), Coding.md5Apache(account.getHashpassword()));
        if (user != null) {
            httpSession.setAttribute(Parameters.USER, user);
            return backPage(user);
        }
        else
        {
            return pagePathManager.getPage(Parameters.EROR_LOGIN_OR_PASSWORD,
                    Message.ERROR_USER_LOGIN_OR_PASSWORD, Page.INDEX);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            try {
                return pagePathManager.getPage(Parameters.USER_LIST, userService.readAll(), Page.USER);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }
}
