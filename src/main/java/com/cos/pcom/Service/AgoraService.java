package com.cos.pcom.Service;


import com.cos.pcom.DTO.Agora.AddCommentDTO;
import com.cos.pcom.DTO.Agora.AgoraDetailDTO;
import com.cos.pcom.DTO.Agora.AgoraHomeDTO;
import com.cos.pcom.DTO.Agora.AgoraPostDTO;
import com.cos.pcom.DTO.CommentDTO;
import com.cos.pcom.Entity.Agora.AgoraTable;
import com.cos.pcom.Entity.Agora.AgoraComment;
import com.cos.pcom.Entity.Agora.Heart;
import com.cos.pcom.Entity.Users;
import com.cos.pcom.Repository.*;
import com.cos.pcom.Repository.comment.AgoraCommentCustomRepositoryJpaImpl;
import com.cos.pcom.Repository.comment.AgoraCommentRepository;
import com.cos.pcom.Repository.heart.AgoraHeartCustomRepositoryJpaImpl;
import com.cos.pcom.Repository.heart.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AgoraService {
    private final AgoraTableRepository agoraTableRepository;
    private final AgoraCommentRepository agoraCommentRepository;
    private final AgoraCustomRepositoryJpaImpl agoraCustomRepositoryJpa;
    private final AgoraHeartCustomRepositoryJpaImpl agoraHeartCustomRepositoryJpa;
    private final HeartRepository heartRepository;
    private final AgoraCommentCustomRepositoryJpaImpl agoraCommentCustomRepositoryJpa;
    private final UserRepository userRepository;

    @Transactional
    public AgoraPostDTO updateAgora(Long id) {
        AgoraTable agoraTable = agoraTableRepository.findById(id).orElseThrow(null);
        AgoraPostDTO agoraPostDTO = AgoraPostDTO.builder()
                .title(agoraTable.getTitle())
                .contents(agoraTable.getContents())
                .build();
        return agoraPostDTO;
    }
    @Transactional
    public void executeUpdate(Long id, AgoraPostDTO agoraPostDTO){
        AgoraTable agoraTable = agoraTableRepository.findById(id).orElseThrow(null);
        agoraTable.updateContent(agoraPostDTO.getTitle(), agoraPostDTO.getContents(),
                LocalDateTime.now());
    }

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
                    .commentsCount(agoraTableList.get(i).getComments().size())
                    .views(agoraTableList.get(i).getViews())
                    .createTime(agoraTableList.get(i).getCreateDate())
                    .build();
            agoraHomeList.add(d);
        }
        return agoraHomeList;
    }
    @Transactional
    public AgoraDetailDTO AgoraDetail(Long userId,Long id){
        AgoraTable ar = agoraTableRepository.findById(id).orElseThrow(null);
        List<Heart> heartList = agoraHeartCustomRepositoryJpa.findHeart(id);
        int heartCount = heartList.size();
        boolean exist = false;
        for(int i =0; i<heartCount; i++){
            if(userId == heartList.get(i).getUsers().getId()){
                exist = true;
                break;
            }
        }
//        int existHeart = heartRepository.countByUserId(userId);
//        boolean exist = false;
//        if(existHeart>0){
//            exist = true;
//        }
        long countVisit = ar.getViews()+1L;
        System.out.println(ar.getId());
        List<AgoraComment> ac = agoraCommentCustomRepositoryJpa.findAllByAgoraTableId(id);
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
                .email(ar.getUsers().getEmail())
                .comments(CDs)
                .createTime(ar.getCreateDate())
                .updateTime(ar.getUpdateDate())
                .likes(heartCount)
                .dolike(exist)
                .views(countVisit)
                .build();
        upView(ar.getId(), ADD);
        return ADD;
    }
    public void AgoraPost(String name, AgoraPostDTO agoraPostDTO){
        Users users =userRepository.findByEmail(name).orElseThrow(null);

        AgoraTable agoraTable = AgoraTable.builder()
                .users(users)
                .contents(agoraPostDTO.getContents())
                .title(agoraPostDTO.getTitle())
                .createDate(LocalDateTime.now())
                .build();
        agoraCustomRepositoryJpa.insertAgoraTable(agoraTable);

    }
    public void AgoraDelete(Long id) {
        agoraCustomRepositoryJpa.deleteAgoraTable(id);

    }
    public void AgoraHeart(Long id, Users users){
        AgoraTable agoraTable = agoraTableRepository.findById(id).orElseThrow(null);
        Heart heart = Heart.builder()
                    .users(users)
                    .agoraTable(agoraTable)
                    .build();
        agoraHeartCustomRepositoryJpa.insertHeart(heart);
    }
    @Transactional
    public void AgoraDeleteHeart(Long userId, Long id) {
        agoraHeartCustomRepositoryJpa.deleteHeart(userId,id);
    }
    @Transactional
    public void addComment(AddCommentDTO addCommentDTO){
        AgoraTable agoraTable = agoraTableRepository.findById(addCommentDTO.getId()).orElseThrow(null);
        AgoraComment agoraComment = new AgoraComment().builder()
                .agoraTable(agoraTable)
                .content(addCommentDTO.getComment())
                .users(addCommentDTO.getUsers())
                .build();
        agoraCommentCustomRepositoryJpa.addComment(agoraComment);
    }


}
