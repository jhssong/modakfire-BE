package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
    List<Donation> findAllByMemberId(String member_id);
}
