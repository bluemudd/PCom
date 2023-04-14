package com.cos.pcom.Repository;

import com.cos.pcom.Entity.Agora.AgoraTable;

import java.util.List;

public interface AgoraCustomRepository {
    List<AgoraTable> findAgoraTable();


}
