package by.andreiblinets.web.mamager;

import by.andreiblinets.constant.Parameters;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ResourceBundle;

@Component
public class PagePathManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(Parameters.CONFIGS_SOURCE);

    private PagePathManager(){}

    public String getProperty(String key){
        return bundle.getString(key);
    }
    public ModelAndView getPage(String parametr, String message, String page) {
        ModelAndView model = new ModelAndView();
        model.setViewName(page);
        if(parametr != null)
        model.addObject(parametr, message);
        return model;
    }
}
