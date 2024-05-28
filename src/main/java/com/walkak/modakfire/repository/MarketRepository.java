package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long> {
}
