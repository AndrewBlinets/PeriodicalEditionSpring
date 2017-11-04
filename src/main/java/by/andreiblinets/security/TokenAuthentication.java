package by.andreiblinets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * Created by Blinec_A on 02.11.2017.
 */

public class TokenAuthentication implements Authentication {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private UserDetails principal;

    public TokenAuthentication(String token, boolean flag) {
        this.token = token;
        this.isAuthenticated =  flag;
       // this.details = request;
    }

    public TokenAuthentication() {
        this.token = null;
    }

    public TokenAuthentication(String token, Collection<GrantedAuthority> authorities, boolean isAuthenticated,
                               UserDetails principal) {
        this.token = token;
        this.authorities = authorities;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }


    @Override
    public String getName() {
        if (principal != null)
            return ((UserDetails) principal).getUsername();
        else
            return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = b;
    }

    public String getToken() {
        return token;
    }

}
