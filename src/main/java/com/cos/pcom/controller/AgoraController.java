package com.cos.pcom.controller;


import com.cos.pcom.DTO.Agora.*;
import com.cos.pcom.DTO.AuthenticationDto;
import com.cos.pcom.Entity.Agora.AgoraTable;
import com.cos.pcom.Entity.Users;
import com.cos.pcom.Repository.AgoraTableRepository;
import com.cos.pcom.Repository.UserRepository;
import com.cos.pcom.Service.AgoraService;
import com.cos.pcom.config.token.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "accesstoken")
@RequiredArgsConstructor
@RestController
public class AgoraController {

    private final AgoraService agoraService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AgoraTableRepository agoraTableRepository;

    @GetMapping("/AgoraHome")
    public ResponseEntity<List<AgoraHomeDTO>> AgoraHome(){
        return ResponseEntity.ok()
                .body(agoraService.AgoraHome());
    }
    @GetMapping("/AgoraDetail/{id}")
    @Transactional
    public ResponseEntity<AgoraDetailDTO> AgoraDetail(@AuthenticationPrincipal Users users, @PathVariable Long id){
        Long userId = users.getId();
        return ResponseEntity.ok()
                .body(agoraService.AgoraDetail(userId, id));
    }
//    @GetMapping("/AgoraFind/{id}")
//    public ResponseEntity<Agora> Agorasas(@PathVariable Long id){
//        return ResponseEntity.ok()
//                .body(agoraService.findByAgoraId(id));
//    }
    @PostMapping("AgoraPostMade/AgoraPost")
    public void AgoraPost(@AuthenticationPrincipal Users users, @RequestBody AgoraPostDTO agoraPostDTO){
       String name = users.getEmail();
       agoraService.AgoraPost(name, agoraPostDTO);

    }
    @DeleteMapping("AgoraPostMade/AgoraDelete/{id}")
    public void AgoraDelete(@PathVariable Long id){

        agoraService.AgoraDelete(id);
    }
    @PostMapping("AgoraPostMade/AgoraComment/{id}")
    public void AgoraComment(@AuthenticationPrincipal Users users, @PathVariable Long id, @RequestBody GetCommentDTO getCommentDTO) {
        AddCommentDTO addCommentDTO = AddCommentDTO.builder()
                        .id(id)
                        .comment(getCommentDTO.getComment())
                        .users(users)
                        .build();
        agoraService.addComment(addCommentDTO);
    }
    @GetMapping("AgoraPostMade/AgoraUpdate/{id}")
    public ResponseEntity<AgoraPostDTO> AgoraUpdate(@AuthenticationPrincipal Users users,@PathVariable Long id){
        System.out.println(users.getNickname());
        AgoraPostDTO agoraPostDTO = agoraService.updateAgora(id);
        return ResponseEntity.ok()
                .body(agoraPostDTO);
    }
    @PatchMapping("AgoraPostMad/ExecuteUpdate/{id}")
    @Transactional
    public void ExecuteUpdate(@AuthenticationPrincipal Users users, @PathVariable Long id, @RequestBody AgoraPostDTO agoraPostDTO){
        agoraService.executeUpdate(id, agoraPostDTO);
    }
    @PostMapping("AgoraPostMade/AgoraHeart/{id}")
    public void AgoraHeart(@AuthenticationPrincipal Users users, @PathVariable Long id){
        agoraService.AgoraHeart(id, users);
    }
    @DeleteMapping("AgoraPostMade/deleteHeart/{id}")
    @Transactional
    public void DeleteHeart(@AuthenticationPrincipal Users users, @PathVariable Long id){
        Long userId = users.getId();
        agoraService.AgoraDeleteHeart(userId, id);
    }

}
