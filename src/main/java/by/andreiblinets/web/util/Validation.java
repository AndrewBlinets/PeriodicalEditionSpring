package by.andreiblinets.web.util;


import by.andreiblinets.constant.Message;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.News;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.PeriodicalEditionDTO;

public class Validation {

    public Validation() {
    }

    public static String validationUser(User user)
    {
        if(user.getSurname().length() < 3 || user.getSurname().length() > 45)
        {
            return  Message.SURNAME_MUST_LENGHT;
        }
        if(user.getName().length() < 3 || user.getName().length() > 45)
        {
            return Message.NAME_MUST_LENGHT;
        }
        return null;
    }

    public static String validationAccount(Account account)
    {
        if(account.getLogin().length() < 3 || account.getLogin().length() > 45)
        {
            return Message.LOGIN_MUST_LENGHT;
        }
        if (account.getLogin().indexOf(' ') != -1)
        {
            return Message.LOGIN_MUST_WITHOUT;
        }
        if(account.getHashpassword().length() < 6)
        {
            return Message.PASSWORD_MUST_LENGHT;
        }
        return null;
    }

    public static String validationNews(News news)
    {
        if(news.getTitle().length() < 3 || news.getTitle().length() > 45 )
        {
            return Message.TITLE_MUST_LENGHT;
        }
        if(news.getAuthor().length() < 3 || news.getAuthor().length() > 45 )
        {
            return Message.AUTHOR_MUST_LENGHT;
        }
        if(news.getBody().length() < 3 || news.getBody().length() > 225 )
        {
            return Message.BODY_MUST_LENGHT;
        }
        return null;
    }

    public static String validationPeriodicalEdition(PeriodicalEditionDTO periodicalEditionDTO)
    {
        Account account = new Account();
        account.setLogin(periodicalEditionDTO.getLogin());
        account.setHashpassword(periodicalEditionDTO.getPassword());
        String message = validationAccount(account);
        if(message != null)
        {
            return message;
        }
        User user = new User();
        user.setSurname(periodicalEditionDTO.getSurname());
        user.setName(periodicalEditionDTO.getName());
        message = validationUser(user);
        if(message != null)
        {
            return message;
        }
        if(periodicalEditionDTO.getNamePeriodicalEdition().length() > 45)
        {
            return Message.NAME_PERIODICAL_EDITION;
        }
        try
        {
            Double.parseDouble(periodicalEditionDTO.getPrice());
        }
        catch (NumberFormatException e)
        {
            return Message.PRICE;
        }
        return null;
    }
}
