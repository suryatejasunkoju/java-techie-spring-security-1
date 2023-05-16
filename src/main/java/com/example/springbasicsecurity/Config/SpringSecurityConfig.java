package com.example.springbasicsecurity.Config;

import com.example.springbasicsecurity.Service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//This annotation tells spring boot to use @preAuthorize
// in Controller to enable role based authorization.

public class SpringSecurityConfig{
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails admin = User.withUsername("surya")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder.encode("password1"))
//                .roles("USER")
//                .build();
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()

//                antMatchers are deprecated and we can use requestMatchers instead of antMatchers
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome").permitAll()

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
}
