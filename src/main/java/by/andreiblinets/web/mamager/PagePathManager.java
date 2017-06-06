package by.andreiblinets.web.mamager;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PagePathManager {

    private PagePathManager(){}

    public ModelAndView getPage(String parametr, Object message, String page) {
        ModelAndView model = new ModelAndView();
        model.setViewName(page);
        if(parametr != null)
        model.addObject(parametr, message);
        return model;
    }
}
