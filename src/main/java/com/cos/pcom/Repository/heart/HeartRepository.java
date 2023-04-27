package com.cos.pcom.Repository.heart;

import com.cos.pcom.Entity.Agora.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    @Query("select count(*) from Heart H where H.users.id=:userId")
    int countByUserId(@Param("userId") Long userId);

}