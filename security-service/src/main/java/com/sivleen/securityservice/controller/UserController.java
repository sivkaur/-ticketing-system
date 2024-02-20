package com.sivleen.securityservice.controller;


import com.sivleen.securityservice.dto.AuthRequest;
import com.sivleen.securityservice.model.UserCredential;
import com.sivleen.securityservice.service.JwtService;
import com.sivleen.securityservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security tutorials !!";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserCredential userCredential){
        return userInfoService.addUser(userCredential);

    }
    @PostMapping("/login")
    public String addUser(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public List<UserCredential> getAllUsers(){
        return userInfoService.getAllUser();
    }
    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public UserCredential getAllUsers(@PathVariable Integer id){
        return userInfoService.getUser(id);
    }
}
