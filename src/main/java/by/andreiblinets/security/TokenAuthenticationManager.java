package by.andreiblinets.security;

import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.exceptions.ServiceException;
import by.andreiblinets.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Blinec_A on 02.11.2017.
 */
@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof TokenAuthentication) {
                TokenAuthentication readyTokenAuthentication = processAuthentication((TokenAuthentication) authentication);
                return readyTokenAuthentication;
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (Exception ex) {
            if(ex instanceof AuthenticationServiceException)
                throw ex;
        }
        return null;
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        String key = "key123";
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }
        if (claims.get("token_expiration_date", Long.class) == null)
            throw new AuthenticationServiceException("Invalid token");
        Date expiredDate = new Date(claims.get("token_expiration_date", Long.class));
        if (expiredDate.after(new Date()))
            return buildFullTokenAuthentication(authentication, claims);
        else
            throw new AuthenticationServiceException("Token expired date error");
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, DefaultClaims claims) {
       // User user = (User) userDetailsService.loadUserByUsername(claims.get("USERNAME", String.class));
        //if (user.isEnabled()) {
        String str = claims.get("clientType").toString();
        if(!str.equals(UserRole.ADMINISTRATOR.toString()) )
        {
            throw new AuthenticationServiceException("rol");
        }
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority((String) claims.get("clientType")));
        User user = new User("qwe","e10adc3949ba59abbe56e057f20f883e",roles);
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            TokenAuthentication fullTokenAuthentication =
                    new TokenAuthentication(authentication.getToken(), roles, true, user);
            return fullTokenAuthentication;
//        } else {
//            throw new AuthenticationServiceException("User disabled");
//        }
    }
}