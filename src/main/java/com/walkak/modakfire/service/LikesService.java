package com.walkak.modakfire.service;

import com.walkak.modakfire.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;

    public Long getLikesCountByCenterId(Long centerId){
        return likesRepository.countByCenterId(centerId);
    }
}
