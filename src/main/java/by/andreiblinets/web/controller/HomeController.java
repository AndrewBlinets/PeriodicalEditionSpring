package by.andreiblinets.web.controller;

import by.andreiblinets.entity.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userBaseService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        boolean flag;
        try {
            flag =  accountService.chekingLogin("qwe");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        User user = null ;
        try {
            user = userBaseService.readById((long)1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Page.INDEX);
        return modelAndView;
    }

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public String goReegistration() {
        return pagePathManager.getProperty(Page.REGISTRATION);
    }

    @RequestMapping (value = "/addcamelcase", method = RequestMethod.GET)
    public String goAddCamelCase() {
        return pagePathManager.getProperty(Page.ADD_CAMEL_CASE);
    }
}
