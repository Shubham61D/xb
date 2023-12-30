package com.wishfy.security;



import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public  class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;


//    @Override
//    protected void doFilterInternal(  HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        //get Token
//        String requestToken = request.getHeader("Authorization");
//        Enumeration<String> headerNames = request.getHeaderNames();
//
//        while (headerNames.hasMoreElements()) {
//            log.info(headerNames.nextElement());
//        }
//
//        //Bearer 2656jksdju55
//
//        log.info(requestToken);
//        String employeeEmail = null;
//        String token = null;
//        if (requestToken != null && requestToken.startsWith("Bearer")) {
//            token = requestToken.substring(7);
//
//
//            try {
//                employeeEmail = this.jwtTokenHelper.getUsernameFromToken(token);
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get Jwt token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("Jwt token has expired");
//            } catch (MalformedJwtException e) {
//                System.out.println("invalid jwt");
//
//            }
//        } else {
//            log.info("Jwt Token Does Not Begin With Bearer");
//        }
//        if (employeeEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(employeeEmail);
//
//            if (jwtTokenHelper.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
//                        (userDetails, null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            } else {
//                log.info("Invalid Jwt Token");
//            }
//        } else {
//            log.info("Username is null of Context is not null");
//        }
//
//    }

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO document why this method is empty

        //get Token
        String requestToken = request.getHeader("Authorization");
        Enumeration<String> headerNames = request.getHeaderNames();

//        while (headerNames.hasMoreElements()) {
//            log.info(headerNames.nextElement());
//        }

        //Bearer 2656jksdju55

        log.info(requestToken);
        String employeeEmail = null;
        String token = null;
        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);


            try {
                employeeEmail = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("invalid jwt");

            }
        } else {
            log.info("Jwt Token Does Not Begin With Bearer");
        }
        if (employeeEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(employeeEmail);

            if (jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                        (userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.info("Invalid Jwt Token");
            }
        } else {
            log.info("Username is null of Context is not null");
        }
    }
}
