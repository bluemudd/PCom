package com.cos.pcom.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false, unique = true)
    private String email;

    @Column(length= 300, nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    // 컬렉션 객체임을 알려주는 어노테이션
    @ElementCollection(fetch = FetchType.EAGER) // roles 컬렉션
    @Builder.Default  // 빌더 기본값 설정
    private List<String> roles = new ArrayList<>();
//    @CollectionTable(name="Authority", joinColumns= @JoinColumn(name="USERS_ID"))
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream()
                .map(SimpleGrantedAuthority :: new)
                .collect(Collectors.toList());
    }
        //orphanRemoval 변경된 자식을 먼저 insert 후 기존의 자식을 delete
        @Override
        public String getUsername() {
            return email;
        }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
