package com.example.securityDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v3/developer/add").hasAuthority("developer")
                        .requestMatchers("/developercode/**").hasAuthority("developer")
                        .requestMatchers("/testcode/**").hasAuthority("qa")
                        .requestMatchers("/generalcode/**").hasAnyAuthority("developer", "qa")
                        .requestMatchers("/home").permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {
                })
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home", true));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(User.builder().username("karan").password(getPE().encode("karan123")).authorities("developer").build())
                .withUser(User.builder().username("ankit").password(getPE().encode("ankit123")).authorities("qa").build());
        return authenticationManagerBuilder.build();
    }

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

}