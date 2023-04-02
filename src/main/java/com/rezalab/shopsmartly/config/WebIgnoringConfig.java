package com.rezalab.shopsmartly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebIgnoringConfig {
    private List<RequestMatcher> matchers = new ArrayList<>();

    @Bean
    public List<RequestMatcher> antMatchers() {
        matchers.add(new AntPathRequestMatcher("/user-account/login", HttpMethod.POST.name()));
        return matchers;
    }
}
