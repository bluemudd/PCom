# 로그인 구현

## JwtAuthenticationFilter

- 로그인 httpServeletRequest을 받으면 dofilter를 
거쳐 토큰을 해석, 유저정보를 SecurityContext에 객체 저장.

## JwtTokenProvider

- 토큰 생성과 파기 및 만료일자 확인.
- nickname과 userPK를 해석하는 메소드 따로 구현
- header에서 토큰값을 가져올때, Authorization에 담아 보냄.

## UserService

- login, join, refresh 메소드 구현.
- 유효기간 만료시, 새로운 토큰 갱신 및 전송.
