package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.UserService;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import by.andreiblinets.web.util.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String aitification(HttpServletRequest request, ModelMap model, @ModelAttribute("registration") Account account) {
        String pagePath = null;
        HttpSession httpSession  = request.getSession();
        User user = (User) httpSession.getAttribute(Parameters.USER);
        if(user != null)
        {
            pagePath = backPage(user, model);
        }
        else {
            if(chekingField(account)) {
                try {
                    pagePath = chekingUser(model, account, httpSession);
                } catch (ServiceException e) {
                    model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                    pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
                }
            }
            else {
                model.addAttribute(Parameters.EROR_LOGIN_OR_PASSWORD, Message.EMPYTY_FIELD);
                pagePath = pagePathManager.getProperty(Page.INDEX);
            }
        }
        return pagePath;
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

    private String backPage(User user, ModelMap model) {
        switch (user.getUserRole())
        {
            case "ADMINISTRATOR":
            {
                model.addAttribute(Parameters.USER, user);
                return pagePathManager.getProperty(Page.ADMIN_MAIN);
            }
            case "READER":
            {
                model.addAttribute(Parameters.USER, user);
                return pagePathManager.getProperty(Page.READER_MAIN);
            }
            case "REDACTOR":
            {
                model.addAttribute(Parameters.USER, user);
                return pagePathManager.getProperty(Page.REDACTOR_MAIN);
            }
            default:
            {
                model.addAttribute(Parameters.EROR_LOGIN_OR_PASSWORD, Message.ERROR);
                return pagePathManager.getProperty(Page.INDEX);
            }
        }
    }

    private String chekingUser(ModelMap model, Account account, HttpSession httpSession) throws ServiceException {
        String pagePath = null;
        User user = accountService.getUser(account.getLogin(), Coding.md5Apache(account.getHashpassword()));
        if (user != null) {
            httpSession.setAttribute(Parameters.USER, user);
            switch (user.getUserRole())
            {
                case "ADMINISTRATOR":
                {
                    model.addAttribute(Parameters.USER, user);
                    pagePath = pagePathManager.getProperty(Page.ADMIN_MAIN);
                    break;
                }
                case "READER":
                {
                    model.addAttribute(Parameters.USER, user);
                    pagePath = pagePathManager.getProperty(Page.READER_MAIN);
                    break;
                }
                case "REDACTOR":
                {
                    model.addAttribute(Parameters.USER, user);
                    pagePath = pagePathManager.getProperty(Page.REDACTOR_MAIN);
                    break;
                }
                default:
                {
                    model.addAttribute(Parameters.EROR_LOGIN_OR_PASSWORD, Message.ERROR);
                    pagePath = pagePathManager.getProperty(Page.INDEX);
                    break;
                }
            }
        }
        else
        {
            model.addAttribute(Parameters.EROR_LOGIN_OR_PASSWORD, Message.ERROR_USER_LOGIN_OR_PASSWORD);
            pagePath = pagePathManager.getProperty(Page.INDEX);
        }
        return pagePath;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
        else {
            String pagePath;
            try {
                model.addAttribute(Parameters.USER_LIST, userService.readAll());
                pagePath = pagePathManager.getProperty(Page.USER);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
    }

//    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
//    public String getUserById(ModelMap model, @PathVariable("id") long id) {
//        String pagePath;
//        try {
//            model.addAttribute(Parameters.USER, userService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.USER);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }

//    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    public String deleteUser(ModelMap model, @PathVariable("id") long id) {
//        String pagePath;
//        try {
//            userService.delete(userService.readById(id));
//            model.addAttribute(Parameters.USER, userService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.USER);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
//
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
//    public String updateUser(ModelMap model, @PathVariable("id") long id, @RequestBody User user) {
//        String pagePath;
//        try {
//            userService.update(user);
//            model.addAttribute(Parameters.USER, userService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.USER);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }

}
