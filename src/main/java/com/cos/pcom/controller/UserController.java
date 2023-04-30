package com.cos.pcom.controller;


import com.cos.pcom.DTO.AuthenticationDto;
import com.cos.pcom.DTO.JoinDto;
import com.cos.pcom.DTO.UserDto;
import com.cos.pcom.config.UserService.UserService;
import com.cos.pcom.config.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "accesstoken")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/join")
    public Long join(@Valid @RequestBody JoinDto joinDto){
        return userService.join(joinDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody UserDto userDto) throws Exception{

        return  userService.login(userDto);
    }
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationDto> test(@RequestHeader HttpHeaders header){
        String token = header.getFirst("Authorization");
        System.out.println(jwtTokenProvider.getUserPk(token));
        System.out.println(jwtTokenProvider.getUserNickname(token));
        return userService.refresh(token);
    }
}
