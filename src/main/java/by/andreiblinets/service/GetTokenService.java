package by.andreiblinets.service;

import by.andreiblinets.entity.User;

/**
 * Created by Blinec_A on 02.11.2017.
 */
public interface GetTokenService {
    String getToken(User user) throws Exception;
}
