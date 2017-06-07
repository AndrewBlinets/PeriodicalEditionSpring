package by.andreiblinets.web.controller;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.entity.dto.UserAndAccount;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.UserService;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.Message;
import by.andreiblinets.constant.Page;
import by.andreiblinets.constant.Parameters;
import by.andreiblinets.web.mamager.PagePathManager;
import by.andreiblinets.web.util.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private PagePathManager pagePathManager;

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public ModelAndView createAccount(@ModelAttribute("registration") UserAndAccount userAndAccount) {
        try {
            if (chekingIsNull(userAndAccount)) {
                if(userAndAccount.getSurname().length() < 3 || userAndAccount.getSurname().length() > 45)
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.SURNAME_MUST_LENGHT, Page.REGISTRATION);
                }
                if(userAndAccount.getName().length() < 3 || userAndAccount.getName().length() > 45)
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.NAME_MUST_LENGHT, Page.REGISTRATION);
                }
                if(userAndAccount.getLogin().length() < 3 || userAndAccount.getLogin().length() > 45)
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.LOGIN_MUST_LENGHT, Page.REGISTRATION);
                }
                if (userAndAccount.getLogin().indexOf(' ') != -1)
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.LOGIN_MUST_WITHOUT, Page.REGISTRATION);
                }
                if(userAndAccount.getPassword().length() < 6)
                {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.PASSWORD_MUST_LENGHT, Page.REGISTRATION);
                }
                if (!accountService.chekingLogin(userAndAccount.getLogin())) {
                    return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.ERROR_LOGIN_EXISTENCE, Page.REGISTRATION);
                } else {
                    userService.create(createUser(userAndAccount));
                    return pagePathManager.getPage(Parameters.OPERATION_MESSAGE, Message.USER_CREATE, Page.REGISTRATION);
                }
            } else {
                return pagePathManager.getPage(Error.ERROR_EXISTENCE_LOGIN, Message.ERROR_FIELD_IS_NULL, Page.REGISTRATION);
            }
        } catch (ServiceException e) {
            return pagePathManager.getPage(Error.ERROR_DATABASE, Message.ERROR_DB, Page.ERROR_PAGE_PATH);
        }
    }


    private User createUser(UserAndAccount userAndAccount) {
        String hashPassword = Coding.md5Apache(userAndAccount.getPassword());
        Account account = new Account();
        account.setLogin(userAndAccount.getLogin());
        account.setHashpassword(hashPassword);
        User user = new User();
        user.setName(userAndAccount.getName());
        user.setSurname(userAndAccount.getSurname());
        user.setUserRole(String.valueOf(UserRole.READER));
        user.setAccount(account);
        return user;
    }

    private boolean chekingIsNull(UserAndAccount userAndAccount) {
        try {
            if (!userAndAccount.getLogin().isEmpty()
                    & !userAndAccount.getPassword().isEmpty()
                    & !userAndAccount.getName().isEmpty()
                    & !userAndAccount.getSurname().isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
}