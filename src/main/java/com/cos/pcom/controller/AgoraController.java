package com.cos.pcom.controller;


import com.cos.pcom.DTO.Agora.AgoraDetailDTO;
import com.cos.pcom.DTO.Agora.AgoraHomeDTO;
import com.cos.pcom.Service.AgoraService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "accesstoken")
@RequiredArgsConstructor
@RestController
public class AgoraController {

    private final AgoraService agoraService;
    @GetMapping("/AgoraHome")
    public ResponseEntity<List<AgoraHomeDTO>> AgoraHome(){
        return ResponseEntity.ok()
                .body(agoraService.AgoraHome());
    }
    @GetMapping("/AgoraDetail/{id}")
    @Transactional
    public ResponseEntity<AgoraDetailDTO> AgoraDetail(@PathVariable Long id){
//        agoraService.updateViews(id);
        return ResponseEntity.ok()
                .body(agoraService.AgoraDetail(id));
    }
//    @GetMapping("/AgoraFind/{id}")
//    public ResponseEntity<Agora> Agorasas(@PathVariable Long id){
//        return ResponseEntity.ok()
//                .body(agoraService.findByAgoraId(id));
//    }
    @PostMapping("/AgoraPost")
    public void AgoraPost(Authentication authentication){
        Claims claims = (Claims)authentication.getPrincipal();
        String name = claims.get("name", String.class);
        System.out.println(name);
    }

}
