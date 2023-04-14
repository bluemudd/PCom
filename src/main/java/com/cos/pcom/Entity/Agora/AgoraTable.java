package com.cos.pcom.Entity.Agora;

import com.cos.pcom.Entity.BaseEntity;
import com.cos.pcom.Entity.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="agora_table")
public class AgoraTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AGORA_ID")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;

    @Column(name="TITLE")
    private String title;

    @Column(name="LIKES")
    private long likes;

    @Column(name="VIEWS", columnDefinition = "long default 0")
    private long views;

    @Column(name="CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name="UPDATE_DATE")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "agoraTable", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<AgoraComment> comments;

    public void updateView(Long views){
        this.views = views;
    }

}