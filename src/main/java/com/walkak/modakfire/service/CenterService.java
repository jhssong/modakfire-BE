package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.dto.CenterForLikeNumDTO;
import com.walkak.modakfire.dto.CenterResponseDTO;
import com.walkak.modakfire.dto.CenterRequestDTO;
import com.walkak.modakfire.repository.CenterRepository;
import com.walkak.modakfire.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;
    private final LikesRepository likesRepository;

    public List<CenterResponseDTO> findCentersByCon(CenterRequestDTO centerRequestDTO){
        List<Center> centers;

        String city = centerRequestDTO.getCity();
        String gu = centerRequestDTO.getGu();
        CenterType centerType = centerRequestDTO.getCenterType();

        if(centerRequestDTO.isAllWhole()) {
            centers = centerRepository.findAll();
        }else if (centerRequestDTO.isCityWhole() && centerRequestDTO.isGuWhole()) {
            centers = centerRepository.findAllByCenterType(centerType);
        }else if (centerRequestDTO.isCityWhole() && centerRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByGu(gu);
        }else if (centerRequestDTO.isGuWhole() && centerRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByCity(city);
        }else if(centerRequestDTO.isCityWhole()){
            centers = centerRepository.findAllByGuAndCenterType(gu,centerType);
        } else if(centerRequestDTO.isGuWhole()){
            centers = centerRepository.findAllByCityAndCenterType(city,centerType);
        } else if(centerRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByCityAndGu(city, gu);
        } else {
            centers = centerRepository.findAllByCityAndGuAndCenterType(city, gu, centerType);
        }
        return centers.stream().map(Center::translate).toList();
    }
    public CenterForLikeNumDTO findCenterById(Long centerId){
        CenterForLikeNumDTO dto = new CenterForLikeNumDTO();
        Center center = centerRepository.findById(centerId).orElseThrow();
        long likeNum = likesRepository.countByCenterId(centerId);
        dto.update(center.translate(),likeNum);
        return dto;
    }

}