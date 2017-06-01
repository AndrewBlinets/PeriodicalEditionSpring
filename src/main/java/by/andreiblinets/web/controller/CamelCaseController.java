package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.entity.Redactor;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.CamelCaseDTO;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.CamelCaseService;
import by.andreiblinets.service.RedactorService;
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

import javax.servlet.http.HttpServletRequest;

@Controller
public class CamelCaseController {

    @Autowired
    private CamelCaseService camelCaseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RedactorService redactorService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/camelcases", method = RequestMethod.GET)
    public String getAllCamelCase(ModelMap model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        String pagePath;
        if(user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            try {
                model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
                pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
        if(user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            try {
                model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
                pagePath = pagePathManager.getProperty(Page.READER_SHOW_CAMEL_CASE_PAGE);
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

    @RequestMapping(value = "/camelcase", method = RequestMethod.POST)
    public String createCamelCase(ModelMap model, @ModelAttribute CamelCaseDTO camelCaseDTO) {
        String pagePath;
        try {
            if(camelCaseService.chekingNameCamelCase(camelCaseDTO.getName()))
            {
                if(accountService.chekingLogin(camelCaseDTO.getLogin()))
                {
                    Redactor redactor = new Redactor();
                    Account account = new Account();
                    account.setLogin(camelCaseDTO.getLogin());
                    account.setHashpassword(Coding.md5Apache(camelCaseDTO.getPassword()));
                    User user = new User();
                    user.setAccount(account);
                    user.setSurname(camelCaseDTO.getSurname());
                    user.setName(camelCaseDTO.getName());
                    user.setUserRole(String.valueOf(UserRole.REDACTOR));
                    CamelCase camelCase = new CamelCase();
                    camelCase.setPrice(camelCaseDTO.getPrice());
                    camelCase.setName(camelCaseDTO.getNameCamelCase());
                    redactor.setCamelCase(camelCase);
                    redactor.setUser(user);
                    redactorService.create(redactor);
                    model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
                    pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
                }
                else
                    {
                        model.addAttribute(Error.ERROR_EXISTENCE_CAMELCASE, Message.ERROR_LOGIN_EXISTENCE);
                        pagePath = pagePathManager.getProperty(Page.ADD_CAMEL_CASE);
                }
            }
            else
            {
                model.addAttribute(Error.ERROR_EXISTENCE_CAMELCASE, Message.ERROR_CAMELCASE_EXISTENCE);
                pagePath = pagePathManager.getProperty(Page.ADD_CAMEL_CASE);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/camelcase/{id}", method = RequestMethod.GET)
    public String getCamelCase(ModelMap model, @PathVariable("id") long id) {
        String pagePath = null;
        try {
            camelCaseService.readById(id);
            //pagePath = pagePathManager.getProperty(Page.)
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/camelcas/{id}", method = RequestMethod.PUT)
    public String updateCamelCase(ModelMap model, @PathVariable("id") long id, @RequestBody CamelCase camelCase) {
       String pagePath = null;
        try {
            camelCaseService.update(camelCase);
            model.addAttribute(Parameters.CAMEL_CASE,camelCaseService.readById(id));
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

   // @RequestMapping(value = "/camelcase/{id}", method = RequestMethod.DELETE)
    @RequestMapping(value = "/camelcase/remove/{id}")
    public String delete(ModelMap model, @PathVariable long id) {
        String pagePath = null;
        try {
            redactorService.delete(redactorService.readById(id));
            if(redactorService.readById(id) == null)
            {
                model.addAttribute(Parameters.MESSAGE, Message.DELETE_CAMELCASE);
                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
            }
            else
            {
                model.addAttribute(Parameters.MESSAGE, Message.NOT_DELETE_CAMELCASE);
                pagePath = pagePathManager.getProperty(Page.ACCOUNT);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

}
