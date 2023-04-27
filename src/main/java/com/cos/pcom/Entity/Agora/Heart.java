package com.cos.pcom.Entity.Agora;

import com.cos.pcom.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "heart")
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long heartId;

    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "AGORA_ID")
    private AgoraTable agoraTable;
}