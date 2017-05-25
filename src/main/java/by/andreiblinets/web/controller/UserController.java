package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.AccountService;
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
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PagePathManager pagePathManager;

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
            userService.delete(userService.readById(id));
            model.addAttribute(Parameters.USER, userService.readById(id));
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String updateUser(ModelMap model, @PathVariable("id") long id, @RequestBody User user) {
        String pagePath;
        try {
            userService.update(user);
            model.addAttribute(Parameters.USER, userService.readById(id));
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

}
