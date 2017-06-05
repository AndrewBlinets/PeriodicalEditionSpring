package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Payment;
import by.andreiblinets.service.PaymentService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String getAllPayment(ModelMap model) {
        String pagePath;
        try {
            model.addAttribute(Parameters.PAYMENT_LIST, paymentService.readAll());
            pagePath = pagePathManager.getProperty(Page.PAYMENT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
    public String getPaymentById(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {
            model.addAttribute(Parameters.PAYMENT, paymentService.readById(id));
            pagePath = pagePathManager.getProperty(Page.PAYMENT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.DELETE)
    public String deletePayment(ModelMap model, @PathVariable("id") long id) {
        String pagePath;
        try {
            paymentService.delete(id);
            model.addAttribute(Parameters.PAYMENT, paymentService.readById(id));
            pagePath = pagePathManager.getProperty(Page.PAYMENT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.PUT)
    public String updatePayment(ModelMap model, @PathVariable("id") long id, @RequestBody Payment payment) {
        String pagePath;
        try {
            paymentService.update(payment);
            model.addAttribute(Parameters.PAYMENT, paymentService.readById(id));
            pagePath = pagePathManager.getProperty(Page.PAYMENT);
        } catch (ServiceException e) {
            model.addAttribute(Error.ERROR_DATABASE, Message.ERROR_DB);
            pagePath = pagePathManager.getProperty(Page.ERROR_PAGE_PATH);
        }
        return pagePath;
    }
}
