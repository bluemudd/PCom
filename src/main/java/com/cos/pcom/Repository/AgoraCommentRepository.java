package com.cos.pcom.Repository;

import com.cos.pcom.Entity.Agora.AgoraComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgoraCommentRepository extends JpaRepository<AgoraComment, Long> {
    Optional<List<AgoraComment>> findAllByAgoraTableId(Long AgoraTableId);
}
