package com.synergisticit.service;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    // co-relate my UserService with Sprnig's UserDetailsService
    @Autowired UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl - loadUserByUsername has been called");
        // when spring loadUserByUsername is called, find my user with username on my db
        
        // this is my user, not spring user
        User user = userService.findUserByUsername(username);
        System.out.println(user.getUsername());
        
        // add user's roles to authority
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println("Role: " + role.getRoleName());
        }
        
        // return spring user
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}