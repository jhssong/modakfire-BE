package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAllByCenterId(Long id);
}
