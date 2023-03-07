package com.cos.pcom.Entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Authority implements GrantedAuthority {// 계정이 가지고 있는 권한 목록 리턴.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String role;


    public static Authority ofUser(User user){
        return Authority.builder()
                .role("ROLE_USER")
                .user(user)
                .build();
    }

    public static Authority ofAdmin(User user){
        return Authority.builder()
                .role("ROLE_ADMIN")
                .user(user)
                .build();
    }

    @Override
    public String getAuthority(){
        return role;
    }
}
