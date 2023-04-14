package com.cos.pcom.controller;

import com.cos.pcom.DTO.JoinDto;
import com.cos.pcom.Entity.Users;
import com.cos.pcom.config.UserService.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//    @Autowired
//    private UserService userService;
//
//    private Logger log = Logger.getLogger(UserControllerTest.class);
//    @Test
//    public void join() throws Exception{
//        JoinDto joinDto = JoinDto.builder()
//                .email("parkjm0817@naver.com")
//                .password("다다이마")
//                .nickname("오카상")
//                .build();
//        String url = "http://localhost:" + this.port +"/join";
//        ResponseEntity<Users> reponseEntity = this.restTemplate.postForEntity(url, joinDto, Users.class);
//
//        Assertions.assertThat(reponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(reponseEntity.getBody().getNickname()).isEqualTo(joinDto.getNickname());
//        log.info("reponseEntity header -> {}", reponseEntity.getHeaders());
//
//    }
}