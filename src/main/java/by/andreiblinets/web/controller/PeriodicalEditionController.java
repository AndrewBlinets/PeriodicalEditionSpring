package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.entity.Editor;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.PeriodicalEditionDTO;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.PeriodicalEditionService;
import by.andreiblinets.service.EditorService;
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

@Controller
public class PeriodicalEditionController {

    @Autowired
    private PeriodicalEditionService periodicalEditionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/camelcases", method = RequestMethod.GET)
    public String getAllCamelCase(ModelMap model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        String pagePath;
        if(user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            try {
                model.addAttribute(Parameters.CAMELCASE_LIST, periodicalEditionService.readAll());
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
                model.addAttribute(Parameters.CAMELCASE_LIST, periodicalEditionService.readAll());
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
    public String createCamelCase(ModelMap model, @ModelAttribute PeriodicalEditionDTO periodicalEditionDTO) {
        String pagePath;
        try {
            if(periodicalEditionService.chekingNameCamelCase(periodicalEditionDTO.getName()))
            {
                if(accountService.chekingLogin(periodicalEditionDTO.getLogin()))
                {
                    Editor editor = new Editor();
                    Account account = new Account();
                    account.setLogin(periodicalEditionDTO.getLogin());
                    account.setHashpassword(Coding.md5Apache(periodicalEditionDTO.getPassword()));
                    User user = new User();
                    user.setAccount(account);
                    user.setSurname(periodicalEditionDTO.getSurname());
                    user.setName(periodicalEditionDTO.getName());
                    user.setUserRole(String.valueOf(UserRole.REDACTOR));
                    PeriodicalEdition periodicalEdition = new PeriodicalEdition();
                    periodicalEdition.setPrice(periodicalEditionDTO.getPrice());
                    periodicalEdition.setName(periodicalEditionDTO.getNameCamelCase());
                    editor.setPeriodicalEdition(periodicalEdition);
                    editor.setUser(user);
                    editorService.create(editor);
                    model.addAttribute(Parameters.CAMELCASE_LIST, periodicalEditionService.readAll());
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
            periodicalEditionService.readById(id);
            //pagePath = pagePathManager.getProperty(Page.)
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/camelcas/{id}", method = RequestMethod.PUT)
    public String updateCamelCase(ModelMap model, @PathVariable("id") long id, @RequestBody PeriodicalEdition periodicalEdition) {
       String pagePath = null;
        try {
            periodicalEditionService.update(periodicalEdition);
            model.addAttribute(Parameters.CAMEL_CASE, periodicalEditionService.readById(id));
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
            editorService.delete(editorService.readById(id));
            if(editorService.readById(id) == null)
            {
                model.addAttribute(Parameters.OPERATION_MESSAGE, Message.DELETE_CAMELCASE);
                pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
            }
            else
            {
                model.addAttribute(Parameters.OPERATION_MESSAGE, Message.NOT_DELETE_CAMELCASE);
                pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
            }
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

}
