package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.dto.CenterResponseDTO;
import com.walkak.modakfire.dto.CenterRequestDTO;
import com.walkak.modakfire.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;

    public List<CenterResponseDTO> findCentersByCon(CenterRequestDTO centerRequestDTO){
        String city = centerRequestDTO.getCity();
        String gu = centerRequestDTO.getGu();
        CenterType centerType = centerRequestDTO.getCenterType();
        List<Center> centers = centerRepository.findAllByCityAndGuAndCenterType(city,gu,centerType);
        return centers.stream().map((center)->center.translate(center)).toList();
    }
    public Center findCenterById(Long id){
        return centerRepository.findById(id).orElseThrow();
    }


}