package com.cos.pcom.Repository.comment;

import com.cos.pcom.Entity.Agora.AgoraComment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AgoraCommentCustomRepositoryJpaImpl implements AgoraCommentCustomRepostiroy{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(AgoraComment agoraComment){
        entityManager.persist(agoraComment);
    }
    @Override
    public List<AgoraComment> findAllByAgoraTableId(Long TableId){
        return entityManager.createQuery("select C from AgoraComment C where C.agoraTable.id = :TableId", AgoraComment.class)
                .setParameter("TableId", TableId)
                .getResultList();

    }
}
