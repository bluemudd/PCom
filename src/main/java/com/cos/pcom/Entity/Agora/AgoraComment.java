package com.cos.pcom.Entity.Agora;

import com.cos.pcom.Entity.BaseEntity;
import com.cos.pcom.Entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agora_comment")
public class AgoraComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agora_table_id")
    private AgoraTable agoraTable;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    public void changeComment(Users users, AgoraTable agoraTable){
        this.users = users;
        this.agoraTable = agoraTable;
    }
}