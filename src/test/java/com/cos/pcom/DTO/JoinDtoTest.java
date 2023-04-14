package com.cos.pcom.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JoinDtoTest {

    @Test
    public void getEmail() {
        final JoinDto joinDto = JoinDto.builder()
                .email("parkjm0817@naver.com")
                .nickname("박정민")
                .build();
        final String email = joinDto.getEmail();
        assertEquals("parkjm0817@naver.com",email);
    }
    @Test
    public void getNickname(){
        final JoinDto joinDto = JoinDto.builder()
                .nickname("뽕짝이")
                .email("parkjm0817@gmail.com")
                .password("123123")
                .build();
        final String nickname = joinDto.getNickname();
        assertEquals("뽕짝이", nickname);
        assertNotNull(joinDto.getPassword());
    }
}