package com.cos.pcom.Repository;

import com.cos.pcom.Entity.Agora.AgoraTable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AgoraCustomRepositoryJpaImpl implements AgoraCustomRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AgoraTable> findAgoraTable(){
        List<AgoraTable> resultList= em.createQuery("SELECT A FROM AgoraTable as A", AgoraTable.class)
                .getResultList();
        return resultList;

    }
    @Override
    public void insertAgoraTable(AgoraTable agoraTable){
        em.persist(agoraTable);
    }
    @Override
    public void deleteAgoraTable(Long id){
        AgoraTable agoraTable = em.find(AgoraTable.class, id);
        em.remove(agoraTable);
    }
}
