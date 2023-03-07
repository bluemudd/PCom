package com.cos.pcom.config.UserService;

import com.cos.pcom.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public static UserDetails of(User user){
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    @Override
    @JsonIgnore //해당 User의 권한 리턴
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername(){
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired(){//계정이 만료되지 않았는지 리턴. true 리턴시 만료되지 않음
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired(){ // 계정의 패스워드 만료여부 리턴. true리턴시 패스워드 만료되지 않음
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled(){ // 사용 가능한 계정인지를 리턴.
        return false;
    }

}
