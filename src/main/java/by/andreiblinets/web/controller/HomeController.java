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
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex() {
        return pagePathManager.getProperty(Page.INDEX);
    }

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public String goReegistration() {
        return pagePathManager.getProperty(Page.REGISTRATION);
    }
}
