package com.travel.notification.service;

import com.travel.notification.model.MyUserDetail;
import com.travel.notification.model.UserModel;
import com.travel.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("no user found !!!");
        }
        return new MyUserDetail(user);
    }
}
