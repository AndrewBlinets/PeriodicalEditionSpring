package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Subscription;
import by.andreiblinets.service.SubscriptionService;
import by.andreiblinets.service.exceptions.ServiceException;
import by.andreiblinets.web.constant.Error;
import by.andreiblinets.web.constant.Message;
import by.andreiblinets.web.constant.Page;
import by.andreiblinets.web.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    public String getAllSubscription(ModelMap model) {
        String pagePath;
        try {
            model.addAttribute(Parameters.SUBSCRIPTION_LIST, subscriptionService.readAll());
            pagePath = pagePathManager.getProperty(Page.SUBSCRIPTION);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.GET)
    public String getSubscriptionById(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {

            model.addAttribute(Parameters.SUBSCRIPTION, subscriptionService.readById(id));
            pagePath = pagePathManager.getProperty(Page.SUBSCRIPTION);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.DELETE)
    public String deleteSubscription(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {
            subscriptionService.delete(subscriptionService.readById(id));
            model.addAttribute(Parameters.USER_LIST, subscriptionService.readAll());
            pagePath = pagePathManager.getProperty(Page.USER);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.PUT)
    public String updateSubscription(ModelMap model, @PathVariable("id") long id, @RequestBody Subscription subscription) {
        String pagePath;
        try {
            subscriptionService.update(subscription);
            model.addAttribute(Parameters.SUBSCRIPTION, subscriptionService.readById(id));
            pagePath = pagePathManager.getProperty(Page.SUBSCRIPTION);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }
}
