package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.Registration;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.UserService;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Error;
import by.andreiblinets.web.constant.Message;
import by.andreiblinets.web.constant.Page;
import by.andreiblinets.web.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(ModelMap model,@ModelAttribute Registration registration) {
        String pagePath;
        try {
            Account account = new Account();
            account.setLogin(registration.getLogin());
            account.setHashpassword(registration.getHashpassword());
            accountService.create(account);
            User user = new User();
            user.setAccount(account);
            user.setSurname(registration.getSurname());
            user.setName(registration.getName());
            userService.create(user);
            model.addAttribute(Parameters.USER_LIST, userService.readAll());
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model) {
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

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {

            model.addAttribute(Parameters.USER, userService.readById(id));
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {
            User user = userService.readById(id);
            userService.delete(user);
            model.addAttribute(Parameters.USER_LIST, userService.readAll());
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

}
