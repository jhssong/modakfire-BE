package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    Long countByCenterId(long centerId);
}
