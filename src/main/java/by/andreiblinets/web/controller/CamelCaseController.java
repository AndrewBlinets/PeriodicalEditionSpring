package by.andreiblinets.web.controller;

import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.service.CamelCaseService;
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
public class CamelCaseController {

    @Autowired
    private CamelCaseService camelCaseService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/camelcases", method = RequestMethod.GET)
    public String getAllCamelCase(ModelMap model) {
        String pagePath;
        try {
            model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
            pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/camelcase", method = RequestMethod.POST)
    public String createCamelCase(ModelMap model, @ModelAttribute CamelCase camelCase) {
        String pagePath;
        try {
            camelCaseService.create(camelCase);
            model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
            pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
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

    @RequestMapping(value = "/camelcase/{id}", method = RequestMethod.DELETE)
    public String delete(ModelMap model, @PathVariable long id) {
        String pagePath = null;
        try {
            CamelCase camelCase = camelCaseService.readById(id);
            camelCaseService.delete(camelCase);
            if(camelCaseService.readById(id) == null)
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
