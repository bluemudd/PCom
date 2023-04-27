package com.cos.pcom.Repository.heart;

import com.cos.pcom.Entity.Agora.Heart;

import java.util.List;

public interface AgoraHeartRepository{
    void insertHeart(Heart heart);

    void deleteHeart(Long userId, Long id);
    List<Heart> findHeart(Long id);



}
