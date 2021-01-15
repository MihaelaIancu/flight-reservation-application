package com.example.flight_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    UserDetailsService userDetailsService;


    @Autowired
    AuthenticationManager authenticationManager;


    public SecurityService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Boolean login(String username,String password){

        boolean state= false;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

        authenticationManager.authenticate(token);
        state = token.isAuthenticated();

        if(state){
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return state;
    }
}