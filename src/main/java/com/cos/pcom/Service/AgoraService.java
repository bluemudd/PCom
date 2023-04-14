package com.cos.pcom.Service;


import com.cos.pcom.DTO.Agora.AgoraDetailDTO;
import com.cos.pcom.DTO.Agora.AgoraHomeDTO;
import com.cos.pcom.DTO.CommentDTO;
import com.cos.pcom.Entity.Agora.AgoraTable;
import com.cos.pcom.Entity.Agora.AgoraComment;
import com.cos.pcom.Repository.AgoraCommentRepository;
import com.cos.pcom.Repository.AgoraTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AgoraService {
    private final AgoraTableRepository agoraTableRepository;
    private final AgoraCommentRepository agoraCommentRepository;
    @Transactional
    public void upView(Long Id, AgoraDetailDTO ADD){
        AgoraTable agoraTable = agoraTableRepository.findById(Id).orElseThrow(null);

        agoraTable.updateView(ADD.getViews());

    }
    public List<AgoraHomeDTO> AgoraHome(){
        List<AgoraTable> agoraTableList = agoraTableRepository.findAll();
        List<AgoraHomeDTO> agoraHomeList = new ArrayList<>();
        for(int i = 0; i< agoraTableList.size(); i++){
            AgoraHomeDTO d = AgoraHomeDTO.builder()
                    .nickname(agoraTableList.get(i).getUsers().getNickname())
                    .id(agoraTableList.get(i).getId())
                    .title(agoraTableList.get(i).getTitle())
                    .likes(agoraTableList.get(i).getLikes())
                    .views(agoraTableList.get(i).getViews())
                    .createTime(agoraTableList.get(i).getCreateDate())
                    .build();
            agoraHomeList.add(d);
        }
        return agoraHomeList;
    }
    public AgoraDetailDTO AgoraDetail(Long id){
        AgoraTable ar = agoraTableRepository.findById(id).get();
        long countVisit = ar.getViews()+1L;
        List<AgoraComment> ac = agoraCommentRepository.findAllByAgoraTableId(ar.getId()).orElseThrow(null);
        List<CommentDTO>  CDs = new ArrayList<>();
        for(int i=0; i<ac.size(); i++){
           CommentDTO CD = CommentDTO.builder()
                   .nickname(ac.get(i).getUsers().getNickname())
                   .id(ac.get(i).getId())
                   .content(ac.get(i).getContent())
                   .createDate(ac.get(i).getCreateTime())
                   .updateDate(ac.get(i).getCreateTime())
                   .build();
           CDs.add(CD);
        }
        AgoraDetailDTO ADD = AgoraDetailDTO.builder()
                .nickname(ar.getUsers().getNickname())
                .title(ar.getTitle())
                .content(ar.getContents())
                .id(ar.getId())
                .comments(CDs)
                .createTime(ar.getCreateDate())
                .updateTime(ar.getUpdateDate())
                .likes(ar.getLikes())
                .views(countVisit)
                .build();
        upView(ar.getId(), ADD);
        return ADD;
    }

}
