package com.cos.pcom.DTO.Agora;

import com.cos.pcom.DTO.CommentDTO;
import com.cos.pcom.Entity.Agora.AgoraComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgoraDetailDTO {
    private Long id;
    private String title;
    private String nickname;
    private String email;
    private String content;
    private List<CommentDTO> comments;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private long likes;
    private boolean dolike;
    private long views;

}
