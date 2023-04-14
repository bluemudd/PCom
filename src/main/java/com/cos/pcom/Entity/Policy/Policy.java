package com.cos.pcom.Entity.Policy;

import com.cos.pcom.Entity.BaseEntity;
import com.cos.pcom.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "policy")
public class Policy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POLICY_ID")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;

    @Column(name="TITLE")
    private String title;

}