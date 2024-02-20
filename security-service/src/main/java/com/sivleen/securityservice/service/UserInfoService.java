package com.sivleen.securityservice.service;


import com.sivleen.securityservice.dao.UserCredentialDao;
import com.sivleen.securityservice.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserCredentialDao userCredentialDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userInfo = userCredentialDao.findByName(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }
    public String addUser(UserCredential userCredential){
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialDao.save(userCredential);
        return "User added successfully";
    }
    public List<UserCredential> getAllUser(){
        return userCredentialDao.findAll();
    }
    public UserCredential getUser(Integer id){
        return userCredentialDao.findById(id).get();
    }
}
