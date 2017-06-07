package by.andreiblinets.web.controller;

import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.UserAndAccount;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex(HttpServletRequest request) {
        request.getSession().invalidate();
        return pagePathManager.getPage(null, null, Page.INDEX);
    }

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public ModelAndView goRegistration() {
        return pagePathManager.getPage(null,null,Page.REGISTRATION);
    }

    @RequestMapping (value = "/addperiodicalEdition", method = RequestMethod.GET)
    public ModelAndView goAddPeriodicalEdition(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null || !user.getUserRole().equals(String.valueOf(UserRole.ADMINISTRATOR)))
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else {
            return pagePathManager.getPage(null, null, Page.PATH_ADD_PERIODICAL_EDITION);
        }
    }
    @RequestMapping(value = "/personalArea", method = RequestMethod.GET)
    public ModelAndView personalArea(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Parameters.USER);
        if(user == null)
        {
            return pagePathManager.getPage(null, null, Page.CONTROL);
        }
        else
        {
            UserAndAccount userAndAccount = new UserAndAccount();
            userAndAccount.setLogin(user.getAccount().getLogin());
            userAndAccount.setName(user.getName());
            userAndAccount.setSurname(user.getSurname());
            return pagePathManager.getPage(Parameters.USER, userAndAccount,Page.PERSONAL_AREA);
        }
    }
}
