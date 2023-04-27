package com.cos.pcom.config.UserService;


import com.cos.pcom.DTO.AuthenticationDto;
import com.cos.pcom.DTO.JoinDto;
import com.cos.pcom.DTO.UserDto;
import com.cos.pcom.Entity.Users;
import com.cos.pcom.Repository.UserRepository;
import com.cos.pcom.config.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public Long join (JoinDto joinDto){
        Users users = Users.builder()
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .nickname(joinDto.getNickname())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
                return userRepository.save(users).getId();
    }
    @Transactional
    public ResponseEntity<AuthenticationDto> login(UserDto userDto){
        Users users = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email"));
        if(!passwordEncoder.matches(userDto.getPassword(), users.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setEmail(users.getEmail());
        authenticationDto.setNickname(users.getNickname());
        authenticationDto.setRoles(users.getRoles());
//        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        return ResponseEntity.ok()
                .header("accesstoken", jwtTokenProvider.CreateToken(users.getUsername(), users.getRoles(),users.getNickname()))
                .body(authenticationDto);
//        return jwtTokenProvider.CreateToken(user.getUsername(), user.getRoles());
    }

    @Transactional
    public ResponseEntity<AuthenticationDto> refresh(String token){
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setEmail(jwtTokenProvider.getUserPk(token));
        authenticationDto.setNickname(jwtTokenProvider.getUserNickname(token));
        authenticationDto.setRoles(jwtTokenProvider.getUserRoles(token));
        if(!jwtTokenProvider.validateToken(token)){
            System.out.println("토큰 무효");
            Users users = userRepository.findByEmail(jwtTokenProvider.getUserPk(token))
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email"));
            String newAccessToken = jwtTokenProvider.CreateToken(users.getUsername(),users.getRoles(),users.getNickname());
            return ResponseEntity.ok()
                    .header("accesstoken", newAccessToken)
                    .body(authenticationDto);
        }
        else{
            System.out.println("토큰 유효");
            return ResponseEntity.ok()
                    .header("accesstoken", token)
                    .body(authenticationDto);
        }

    }

}
