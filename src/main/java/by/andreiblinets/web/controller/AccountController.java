package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.Registration;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.UserService;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Error;
import by.andreiblinets.web.constant.Message;
import by.andreiblinets.web.constant.Page;
import by.andreiblinets.web.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import by.andreiblinets.web.util.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private PagePathManager pagePathManager;

   /* @RequestMapping(value = "/account", method = RequestMethod.GET)
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
    }*/

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(ModelMap model, @ModelAttribute("registration") Registration registration) {
        String pagePath;
        try {
            if(!accountService.chekingLogin(registration.getLogin()))
            {
                model.addAttribute(Error.ERROR_EXISTENCE_LOGIN, Message.ERROR_LOGIN_EXISTENCE);
                pagePath = pagePathManager.getProperty(Page.REGISTRATION);
            }
            else {
                String hashPassword = Coding.md5Apache(registration.getHashpassword());
                Account account = new Account();
                account.setLogin(registration.getLogin());
                account.setHashpassword(hashPassword);
                User user = new User();
                user.setName(registration.getName());
                user.setSurname(registration.getSurname());
                user.setUserRole(String.valueOf(UserRole.READER));
                user.setAccount(account);
                userService.create(user);
                model.addAttribute(Parameters.OPERATION_MESSAGE,Message.USER_CREATE);
                pagePath = pagePathManager.getProperty(Page.REGISTRATION);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

//    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
//    public String getAccount(ModelMap model, @PathVariable("id") long id) {
//        String pagePath;
//        try {
//            model.addAttribute(Parameters.ACCOUNT, accountService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.ACCOUNT);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
//
//    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
//    public String updateAccount(ModelMap model, @PathVariable("id") long id, @RequestBody Account account) {
//        String pagePath;
//        try {
//            accountService.update(account);
//            model.addAttribute(Parameters.ACCOUNT, accountService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.ACCOUNT);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
//
//    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
//    public String deleteAccount(ModelMap model, @PathVariable long id) {
//        String pagePath;
//        try {
//            accountService.delete(accountService.readById(id));
//            if (accountService.readById(id) == null)
//            {
//                model.addAttribute(Parameters.MESSAGE, Message.DELETE_ACCOUNT);
//                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
//            }
//            else
//            {
//                model.addAttribute(Parameters.MESSAGE, Message.NOT_DELETE_ACCOUNT);
//                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
//            }
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }

}
