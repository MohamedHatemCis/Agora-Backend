package com.training.agora.security;

import com.training.agora.authenticate.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        // The list of the permitted urls which can use without authenticate
      String [] ACCEPTABLE_URLS={"/api/v1/agora/login","/api/v1/agora/register","/api/v1/agora/aboutUs"};

      //Enable cors to can use it from third party and disable csrf to enable post and delete requests
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                //Permit the acceptable requests only
                .requestMatchers(ACCEPTABLE_URLS).permitAll()
                //Prevent using another endpoint until authenticating
                .anyRequest().authenticated()
                .and()
                //Stateless Policy is make each request doesn't depend on each other
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                //Use Jwt filter before authentication filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
