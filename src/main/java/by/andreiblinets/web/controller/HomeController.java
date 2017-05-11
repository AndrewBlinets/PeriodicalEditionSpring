package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private BaseService<User> userBaseService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        Account account = null;
        try {
            account =  accountService.readById((long)1);
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
}
