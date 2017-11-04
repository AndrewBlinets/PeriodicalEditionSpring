package by.andreiblinets.service.impl;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.enums.UserRole;
import by.andreiblinets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Blinec_A on 03.11.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = userService.getAccount(s);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRole.ADMINISTRATOR.name()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getLogin(),
                        account.getHashpassword(),
                        roles);

        return userDetails;
    }
}
