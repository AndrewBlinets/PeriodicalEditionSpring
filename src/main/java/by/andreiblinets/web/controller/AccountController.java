package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.service.AccountService;
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
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAllAccount(ModelMap model) {
        String pagePath = null;
        try {
                accountService.readAll();
           // model.addAttribute(Parameters., camelCaseService.readAll());
            //pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_PAGE);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createAccount(ModelMap model, @ModelAttribute Account account) {
        String pagePath;
        try {
            if(accountService.chekingLogin(account.getLogin()))
            {
                model.addAttribute(Error.ERROR_EXISTENCE_LOGIN, Message.ERROR_LOGIN_EXISTENCE);
                pagePath = pagePathManager.getProperty(Page.REGISTRATION);
            }
            else {
                accountService.create(account);
                pagePath = pagePathManager.getProperty(Page.INDEX);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String getAccount(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {
            model.addAttribute(Parameters.ACCOUNT, accountService.readById(id));
            pagePath = pagePathManager.getProperty(Page.ACCOUNT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public String saveCamelCase(ModelMap model, @PathVariable("id") long id, @RequestBody Account account) {
        String pagePath;
        try {
            accountService.update(account);
            model.addAttribute(Parameters.ACCOUNT, accountService.readById(id));
            pagePath = pagePathManager.getProperty(Page.ACCOUNT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public String delete(ModelMap model, @PathVariable long id) {
        String pagePath;
        try {
            accountService.delete(accountService.readById(id));
            if (accountService.readById(id) == null)
            {
                model.addAttribute(Parameters.MESSAGE, Message.DELETE_ACCOUNT);
                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
            }
            else
            {
                model.addAttribute(Parameters.MESSAGE, Message.NOT_DELETE_ACCOUNT);
                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

}
