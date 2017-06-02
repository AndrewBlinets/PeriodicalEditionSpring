package by.andreiblinets.web.controller;

import by.andreiblinets.entity.User;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request) {
        request.getSession().invalidate();
        return pagePathManager.getProperty(Page.INDEX);
    }

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public String goReegistration() {
        return pagePathManager.getProperty(Page.REGISTRATION);
    }

    @RequestMapping (value = "/addcamelcase", method = RequestMethod.GET)
    public String goAddCamelCase(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            return pagePathManager.getProperty(Page.CONTROL);
        }
        else {
            return pagePathManager.getProperty(Page.ADD_CAMEL_CASE);
        }
    }
}
