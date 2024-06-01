package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Likes;
import com.walkak.modakfire.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    Long countByCenterId(long centerId);
    boolean existsByCenterIdAndMemberId(long centerId,String memberId);
    Optional<Likes> findByMemberAndCenter(Member member,Center center);
}
