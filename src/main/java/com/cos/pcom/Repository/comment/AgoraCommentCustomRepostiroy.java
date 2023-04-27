package com.cos.pcom.Repository.comment;

import com.cos.pcom.Entity.Agora.AgoraComment;

import java.util.List;

public interface AgoraCommentCustomRepostiroy {
    void addComment(AgoraComment comment);
    List<AgoraComment> findAllByAgoraTableId(Long TableId);
}
