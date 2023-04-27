package com.cos.pcom.config;


import com.cos.pcom.config.UserService.CustomUserDetailService;
import com.cos.pcom.config.token.JwtAuthenticationFilter;
import com.cos.pcom.config.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class SecurityConfig {

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
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
//        configuration.addAllowedMethod("*");
        configuration.setAllowedMethods(new ArrayList<>(Arrays.asList("POST","GET","PUT","DELETE","PATCH","OPTIONS")));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        //사용자 자격 증명이 지원되는지 여부
        configuration.setAllowCredentials(true);
        configuration.setMaxAge((long) 3600);
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("accessToken");
        configuration.addExposedHeader("content-disposition");


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http,JwtTokenProvider jwtTokenProvider) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource())
                .and()
                //h2 콘솔 사용
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 옵션을 disable
                .and()
                //세션 자동생성 끄기
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .csrf().ignoringRequestMatchers(
//                        new AntPathRequestMatcher("/h2-console/**"))
                //URL 관리
                .authorizeHttpRequests()
                .antMatchers("/refresh","/AgoraFind/{id}","/AgoraDetail/{id}","/AgoraHome","/join", "/login", "/h2-console/**").permitAll()
                .antMatchers("/AgoraPostMade/**","/AgoraPostMade/AgoraUpdate/{id}").hasRole("USER")
                .anyRequest().authenticated();

                //JwtAuthenticationFilter 먼저적용

       return http.build();
    }
//        @Bean
//        public SecurityFilterChain configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//            return auth;
//        }
    }


