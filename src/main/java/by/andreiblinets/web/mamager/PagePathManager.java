package by.andreiblinets.web.mamager;

import by.andreiblinets.constant.Parameters;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class PagePathManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(Parameters.CONFIGS_SOURCE);

    private PagePathManager(){}

    public String getProperty(String key){
        return bundle.getString(key);
    }
}
