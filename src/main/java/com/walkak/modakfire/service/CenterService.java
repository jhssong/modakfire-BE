package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.dto.SimpleCenterResponseDTO;
import com.walkak.modakfire.dto.HomeRequestDTO;
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

    public List<SimpleCenterResponseDTO> findCentersByCon(HomeRequestDTO homeRequestDTO){
        List<Center> centers;
        String city = homeRequestDTO.getCity();
        String gu = homeRequestDTO.getGu();
        CenterType centerType = homeRequestDTO.getCenterType();
        if(homeRequestDTO.isAllWhole()) {
            centers = centerRepository.findAll();
        }else if (homeRequestDTO.isCityWhole() && homeRequestDTO.isGuWhole()) {
            centers = centerRepository.findAllByCenterType(centerType);
        }else if (homeRequestDTO.isCityWhole() && homeRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByGu(gu);
        }else if (homeRequestDTO.isGuWhole() && homeRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByCity(city);
        }else if(homeRequestDTO.isCityWhole()){
            centers = centerRepository.findAllByGuAndCenterType(gu,centerType);
        } else if(homeRequestDTO.isGuWhole()){
            centers = centerRepository.findAllByCityAndCenterType(city,centerType);
        } else if(homeRequestDTO.isCenterTypeWhole()) {
            centers = centerRepository.findAllByCityAndGu(city, gu);
        } else {
            centers = centerRepository.findAllByCityAndGuAndCenterType(city, gu, centerType);
        }
        return centers.stream().map(Center::translate).toList();
    }
    public Center findCenterById(Long id){
        return centerRepository.findById(id).orElseThrow();
    }


}