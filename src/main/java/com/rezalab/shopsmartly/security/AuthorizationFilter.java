package com.rezalab.shopsmartly.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtComponent jwtComponent;

    @Autowired
    private List<RequestMatcher> antMatchers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long count = antMatchers.stream().filter(matcher -> matcher.matches(request)).collect(Collectors.counting());
        if (count == 0 && !request.getRequestURI().equals("/user-account/login")) {
            String authorization = request.getHeader("Authorization");
            String[] splitAuth = authorization.split(" ");
            String token = splitAuth[1];

            try {
                String id = jwtComponent.getClaimId(token);
                Authentication auth = new UsernamePasswordAuthenticationToken(id, null);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                response.setStatus(401);
                Response errorRes = new Response();
                errorRes.setData(null);
                errorRes.setMessage("Token hangus");
                response.getWriter().append(objectMapper.writeValueAsString(errorRes));
                response.setContentType("application/json");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
