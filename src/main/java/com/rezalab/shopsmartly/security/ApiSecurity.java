package com.rezalab.shopsmartly.security;

import com.rezalab.shopsmartly.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private List<RequestMatcher> antMatchers;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private
}
