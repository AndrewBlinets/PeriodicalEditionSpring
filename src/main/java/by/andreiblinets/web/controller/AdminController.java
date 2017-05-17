package by.andreiblinets.web.controller;

import by.andreiblinets.entity.User;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Error;
import by.andreiblinets.web.constant.Message;
import by.andreiblinets.web.constant.Page;
import by.andreiblinets.web.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BaseService<User> userService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String showReaders(ModelMap model) {
        String pagePath;
        try {
            List<User> list = userService.readAll();
            model.addAttribute(Parameters.USER_LIST, list);
            pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_READER_PAGE);
        }
        catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/readerdelete/{id}", method = RequestMethod.DELETE)
    public String deleteReader(ModelMap model,@PathVariable("id") long id) {
        String pagePath;
        try {
            User user = userService.readById(id);
            userService.delete(user);
            model.addAttribute(Parameters.USER, user);
            pagePath = pagePathManager.getProperty(Page.ADMIN_DELETE_READER_PAGE);
        }
        catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

   /* @RequestMapping(value = "/reader/{id}", method = RequestMethod.GET)
    public String showReader(ModelMap model,@PathVariable("id") long id) {
        String pagePath;
        try {

            User user = userService.readById(id);
           // List<User> list = null;
           // list.add(user);
            model.addAttribute(Parameters.USER_LIST, user);
            pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_READER_PAGE);
        }
        catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }*/
}
