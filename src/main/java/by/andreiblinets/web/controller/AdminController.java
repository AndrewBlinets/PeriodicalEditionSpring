package by.andreiblinets.web.controller;

import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.entity.User;
import by.andreiblinets.web.mamager.PagePathManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Blinec_A on 03.11.2017.
 */
@Controller
@RequestMapping("/rest")
public class AdminController {

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getAllNews() {
        return pagePathManager.getPage(Parameters.USER_LIST,  new ArrayList<>(Arrays.asList(new User("qwe", "asd"))), Page.USER);
    }
}
