package com.example.minorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.minorProject.models.MyUser;
import com.example.minorProject.repository.MyUserCacheRepository;
import com.example.minorProject.repository.MyUserRepositoryInterf;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepositoryInterf myUserRepositoryInterf;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserCacheRepository.get(username);
        if(myUser == null){
            myUser = myUserRepositoryInterf.findByUsername(username);

            if(myUser != null){
                myUserCacheRepository.set(myUser);
            }
        }
        return myUser;
    }

    public void createUser(MyUser myUser) {
        myUserRepositoryInterf.save(myUser);
    }

}
