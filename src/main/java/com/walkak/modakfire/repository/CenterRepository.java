package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center,Long> {
    List<Center> findAllByCityAndGuAndCenterType(String city,String gu, CenterType centerType);
    List<Center> findAllByCity(String city);
    List<Center> findAllByGu(String gu);
    List<Center> findAllByCenterType(CenterType centerType);
    List<Center> findAllByCityAndGu(String city,String gu);
    List<Center> findAllByCityAndCenterType(String city,CenterType centerType);
    List<Center> findAllByGuAndCenterType(String gu,CenterType centerType);
    Center findByPeriodicalDonationId(Long periodicalDonationId);
}
