package com.example.springbasicsecurity.Config;

import com.example.springbasicsecurity.Service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//since, we are configuring spring security, we have to use @Configuration.
@EnableWebSecurity
@EnableMethodSecurity//This annotation tells spring boot to use @preAuthorize
// in Controller to enable role based authorization.
public class SpringSecurityConfig{
    //Difference btw spring 2 and 3 : We have to create beans of all required methods in spring 3.
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("surya")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder.encode("password1"))
//                .roles("USER")
//                .build();
        return new UserInfoService();
    }
//     Spring will call this method to obtain the instance of UserDetailsService
//     whenever it is needed throughout the application. Because we used @Bean annotation.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()

//                antMatchers are deprecated and we can use requestMatchers instead of antMatchers
                .authorizeHttpRequests()
                .requestMatchers("/products/new")
                .permitAll()

                .and()

                .authorizeHttpRequests()
                .requestMatchers("/products/**")
                .authenticated()
                .and()
                .formLogin()

                .and()
                .build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
