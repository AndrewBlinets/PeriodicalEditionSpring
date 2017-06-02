package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Subscription;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.PeriodicalEditionService;
import by.andreiblinets.service.SubscriptionService;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PeriodicalEditionService periodicalEditionService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/subscription/create/{id}", method = RequestMethod.GET)
    public String createSubscription(ModelMap model, HttpServletRequest request,@PathVariable("id") long id ) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
        else {
            String pagePath;
            try {
                Subscription subscription = new Subscription();
                subscription.setPeriodicalEdittion(periodicalEditionService.readById(id));
                subscription.setUser(user);
                subscriptionService.create(subscription);
                model.addAttribute(Parameters.OPERATION_MESSAGE, Message.SUBSCRIPTION_SUCSECC);
                pagePath = pagePathManager.getProperty(Page.READER_MAIN);
            } catch (ServiceException e) {
                model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
                pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
            }
            return pagePath;
        }
    }

//    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.GET)
//    public String getSubscriptionById(ModelMap model, @PathVariable("id") long id) {
//        String pagePath;
//        try {
//            model.addAttribute(Parameters.SUBSCRIPTION, subscriptionService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.SUBSCRIPTION);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
//
//    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.DELETE)
//    public String deleteSubscription(ModelMap model, @PathVariable("id") long id) {
//        String pagePath;
//        try {
//            subscriptionService.delete(subscriptionService.readById(id));
//            model.addAttribute(Parameters.USER_LIST, subscriptionService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.USER);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
//
//    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.PUT)
//    public String updateSubscription(ModelMap model, @PathVariable("id") long id, @RequestBody Subscription subscription) {
//        String pagePath;
//        try {
//            subscriptionService.update(subscription);
//            model.addAttribute(Parameters.SUBSCRIPTION, subscriptionService.readById(id));
//            pagePath = pagePathManager.getProperty(Page.SUBSCRIPTION);
//        } catch (ServiceException e) {
//            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
//            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
//        }
//        return pagePath;
//    }
}
