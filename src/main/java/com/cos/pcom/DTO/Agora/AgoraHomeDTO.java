package com.cos.pcom.DTO.Agora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgoraHomeDTO {
    private Long id;
    private String title;
    private String nickname;
    private long likes;
    private long views;
    private LocalDateTime createTime;

}
