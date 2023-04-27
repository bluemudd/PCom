package com.cos.pcom.config.token;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//Jwt 커스텀 필터 생성 request, response 처리
// http 요청이 들어오면 가장 먼저 거치게될 필터.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 토큰 받아오기
        // HttpServletRequest를 사용하면, 값을 받아올수있는데
        // 만약 회원 정보를 컨트롤러로 보냈을때, HttpServletRequest 객체 안에 데이터들이 들어감.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request );
        // 토큰이 유효하다면
        if(token != null && jwtTokenProvider.validateToken(token)){
            // 토큰으로부터 유저 정보를 받아
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            System.out.println(authentication);
            // SecurityContext 에 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 다음 Filter 실행.
        chain.doFilter(request, response);

    }

}
