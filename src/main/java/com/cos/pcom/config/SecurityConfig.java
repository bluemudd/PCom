package com.cos.pcom.config;


import com.cos.pcom.config.UserService.CustomUserDetailService;
import com.cos.pcom.config.token.JwtAuthenticationFilter;
import com.cos.pcom.config.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().antMatchers("/h2-console/**", "favicon.ico");
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                //h2 콘솔 사용
                .csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 옵션을 disable
                .and()
                //세션 자동생성 끄기
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //URL 관리
                .authorizeRequests()
                .antMatchers("/join", "/login", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()

                //JwtAuthenticationFilter 먼저적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }
//        @Bean
//        public SecurityFilterChain configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//            return auth;
//        }
    }


