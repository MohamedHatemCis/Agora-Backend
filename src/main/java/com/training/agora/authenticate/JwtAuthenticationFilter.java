package com.training.agora.authenticate;

import com.training.agora.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the value of the Authorization
        final String header=request.getHeader("Authorization");
        String token;
        //Token should start with "Bearer " to be in a valid format
        if(header==null||!header.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
        }
        else
        {
            //explode "Bearer " from the Authorization header
            token=header.substring("Bearer ".length());
            // Get the username from the token
            String username=jwtService.extractUsername(token);
            //Check if there is a username and there isn't any authenticated token before
            if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
            {
                //Get all user details with the username
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                //check the validity of the token
                if(jwtService.isValidToken(token,userDetails)){
                    //TODO Authenticate and set the user as authenticated
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
    }
}
