package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.PeriodicalDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicalDonationRepository extends JpaRepository<PeriodicalDonation,Long> {
    List<PeriodicalDonation> findAllByMemberId(String memberId);
}
