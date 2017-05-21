package by.andreiblinets.web.controller;

import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.entity.dto.CamelCaseDTO;
import by.andreiblinets.service.BaseService;
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
import org.springframework.web.servlet.ModelAndView;

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
    public String createCamelCase(ModelMap model, @ModelAttribute CamelCaseDTO camelCaseDTO) {
        String pagePath;
        try {
            CamelCase camelCase = new CamelCase();
            camelCase.setName(camelCaseDTO.getName());
            camelCase.setPrice(Long.parseLong(camelCaseDTO.getPrice()));
            camelCaseService.create(camelCase);
            model.addAttribute(Parameters.CAMELCASE_LIST, camelCaseService.readAll());
            pagePath = pagePathManager.getProperty(Page.ADMIN_SHOW_CAMEL_CASE_PAGE);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    /*@RequestMapping(value = "/camelcase/{id}", method = RequestMethod.GET)
    public ModelAndView getCamelCase(@PathVariable("id") long id) {
        String pagePath;
        try {
            camelCaseService.readById(id);
            return null;
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return page;
    }

    @RequestMapping(value = "/camelcases", method = RequestMethod.POST)
    public ModelAndView saveCamelCase(@RequestBody CamelCase camelCase) {
        try {
            camelCaseService.update(camelCase);
            return null;
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return page;
    }

    @RequestMapping(value = "/camelcase/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        try {
            CamelCase camelCase = camelCaseService.readById(id);
            camelCaseService.delete(camelCase);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return page;
    }*/

}
