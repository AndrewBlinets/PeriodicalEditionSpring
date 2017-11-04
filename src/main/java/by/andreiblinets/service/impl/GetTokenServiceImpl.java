package by.andreiblinets.service.impl;


import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.GetTokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Blinec_A on 02.11.2017.
 */
@Service
public class GetTokenServiceImpl implements GetTokenService {

    @Override
    public String getToken(User user) throws Exception {
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", user.getUserRole().toString());
        tokenData.put("userId", user.getId());
        tokenData.put("name", user.getName());
        tokenData.put("surname", user.getSurname());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "key123";
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
    }
}
