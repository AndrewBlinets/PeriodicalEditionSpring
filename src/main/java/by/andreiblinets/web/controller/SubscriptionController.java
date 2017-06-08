package by.andreiblinets.web.controller;

import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.service.PeriodicalEditionService;
import by.andreiblinets.service.SubscriptionService;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PeriodicalEditionService periodicalEditionService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/subscription/create/{id}", method = RequestMethod.GET)
    public ModelAndView createSubscription(HttpServletRequest request, @PathVariable("id") long id ) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            try {
                Subscription subscription = new Subscription();
                subscription.setPeriodicalEdition(periodicalEditionService.readById(id));
                subscription.setUser(user);
                subscriptionService.create(subscription);
                return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.SUBSCRIPTION_SUCSECC,
                        Page.READER_MAIN);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }

    @RequestMapping(value = "/subscription/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeSubscription(HttpServletRequest request, @PathVariable("id") long id ) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            try {
                subscriptionService.delete(id);
                return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.SUBSCRIPTION_DELETE,
                        Page.READER_MAIN);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public ModelAndView mySubscription(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.READER)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            try {
                List<PeriodicalEdition> periodicalEditions = new ArrayList<>();
                List<Subscription> subscriptions = subscriptionService.getSubscribtionByIdUser(user.getId());
                for (Subscription sub : subscriptions) {
                    PeriodicalEdition periodicalEdition = periodicalEditionService.readById(sub.getPeriodicalEdition().getId());
                    periodicalEdition.setId(sub.getId());
                    periodicalEditions.add(periodicalEdition);

                }
                return pagePathManager.getPage(Parameters.PERIODICAL_EDITION_LIST, periodicalEditions,
                        Page.READER_SUBSCRIPTION);
            } catch (ServiceException e) {
                return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
            }
        }
    }
}
