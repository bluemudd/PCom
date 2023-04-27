package com.cos.pcom.DTO.Agora;

import com.cos.pcom.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgoraPostDTO {
    String title;
    String contents;
}
