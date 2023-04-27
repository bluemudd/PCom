package com.cos.pcom.Repository.heart;

import com.cos.pcom.Entity.Agora.Heart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AgoraHeartCustomRepositoryJpaImpl implements AgoraHeartRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertHeart(Heart heart){
        entityManager.persist(heart);
    }

    @Override
    public void deleteHeart(Long userId, Long id){
        entityManager.createQuery("delete from Heart H where H.agoraTable.id=:id and H.users.id = :userId")
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
        entityManager.clear();
    }
    @Override
    public List<Heart> findHeart(Long id){
        return entityManager.createQuery("select H from Heart H where H.agoraTable.id=:id", Heart.class)
                .setParameter("id", id)
                .getResultList();
    }
//    public Long findByUserId(Long userId){
//        return entityManager.createQuery("select count(*) from Heart H where H.users.id=:userId", Long.class)
//                .setParameter("userId", userId)
//                .getSingleResult();
//    }
}
