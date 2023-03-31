package com.rezalab.shopsmartly.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtComponent jwtComponent;

    @Autowired
    private List<RequestMatcher> antMatchers;


}
