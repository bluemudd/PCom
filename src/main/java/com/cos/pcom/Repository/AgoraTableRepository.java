package com.cos.pcom.Repository;

import com.cos.pcom.Entity.Agora.AgoraTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface AgoraTableRepository extends JpaRepository<AgoraTable,Long> {
    Optional<AgoraTable> findById(Long id);

//    @Query(value="select * from AGORA where AGORA.AGORA_ID = :id", nativeQuery = true)
//    Agora findByAgoraId(Long id);
//    @Transactional
//    @Modifying
//    @Query(value = "update agora_table a set a.views = a.views+1 where a.agora_id = :id", nativeQuery = true)
//    int updateByAgoraId(Long id);
}
