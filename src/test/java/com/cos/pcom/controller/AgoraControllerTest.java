package com.cos.pcom.controller;

import static org.mockito.BDDMockito.given;


import com.cos.pcom.DTO.Agora.AgoraDetailDTO;
import com.cos.pcom.DTO.JoinDto;
import com.cos.pcom.Service.AgoraService;
import com.cos.pcom.config.UserService.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AgoraController.class)
public class AgoraControllerTest {

        @Autowired
        private MockMvc mockMVc;

        @MockBean
        AgoraService agoraService;


//        @Test
//        @DisplayName("Agora 홈화면")
//        void AgoraDetailTest() throws Exception{
//                //given : Mock 객체가 특저 상황에 해야하는 행위를 정의.
//                given(agoraService.AgoraDetail(1L)).willReturn(
//                        new AgoraDetailDTO()
//                )
//        }















//        @LocalServerPort
//        private int port;
//
//        @Autowired
//        private TestRestTemplate restTemplate;
//        @Autowired
//        private UserService userService;
//
//        @Test
//        public void join() throws Exception{
//                JoinDto joinDto = JoinDto.builder()
//                        .email("parkjm0817@naver.com")
//                        .password("다다이마")
//                        .nickname("오카상")
//                        .build();
//                String url = "http://localhost:" + this.port +"/join";
//        }



}