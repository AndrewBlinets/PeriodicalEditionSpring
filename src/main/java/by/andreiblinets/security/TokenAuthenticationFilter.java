package by.andreiblinets.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Blinec_A on 02.11.2017.
 */

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    public TokenAuthenticationFilter() {
        super("/rest/*");
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            System.out.println("qwe");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String string = request.getServletPath();
            string = request.getPathInfo();
            request.getRequestDispatcher(request.getServletPath()).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            System.out.println("asd");
           //request.getRequestDispatcher("/index").include(request,response);
           request.getRequestDispatcher("/index").forward(request,response);
            // response.getOutputStream().print(authenticationException.getMessage());
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("token!                      !!!!!!!!!!!!!!!!!!");
        String token = (String) request.getSession().getAttribute("token");
        if(token != null) {
            TokenAuthentication tokenAuthentication = new TokenAuthentication(token, true);
            Authentication authentication = new TokenAuthenticationManager().authenticate(tokenAuthentication);
            //Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
            return authentication;
        }
        else
            throw new AuthenticationServiceException("token null");
    }
}

