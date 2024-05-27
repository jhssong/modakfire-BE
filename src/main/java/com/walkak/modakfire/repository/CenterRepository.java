package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center,Long> {
    List<Center> findAllByCityAndGuAndCenterType(String city,String gu, CenterType centerType);
}
