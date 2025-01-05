package com.example.securityInDatabaseDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers(POST,"/api/v3/developer/add").hasAuthority("developer")
                        .requestMatchers("/developercode/**").hasAuthority("developer")
                        .requestMatchers("/testcode/**").hasAuthority("qa")
                        .requestMatchers("/generalcode/**").hasAnyAuthority("developer", "qa")
                        .requestMatchers("/home").permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {
                })
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home", true).permitAll())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

    // Role based permissions: Request Matchers will have multiple roles defined for set of API's
    //                          and user in database will have single role
    // Action based permission: Request Matcher will have single role for set of API's and
    //                          user in db will have multiple actions in authority column
    // Combination of both: Ideally suitable and preferable

}