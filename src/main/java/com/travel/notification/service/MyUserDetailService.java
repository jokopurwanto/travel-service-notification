package com.travel.notification.service;

import com.travel.notification.db.notificationdb.model.MyUserAuthDetail;
import com.travel.notification.db.notificationdb.model.UserAuthModel;
import com.travel.notification.db.notificationdb.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthModel user = userAuthRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("no user found !!!");
        }
        return new MyUserAuthDetail(user);
    }
}
