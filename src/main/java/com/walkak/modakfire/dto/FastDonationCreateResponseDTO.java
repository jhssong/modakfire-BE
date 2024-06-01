package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FastDonationCreateResponseDTO {

    private List<SimpleDonationResponseDTO> donationResponseDTOList = new ArrayList<>();

    public void add(SimpleDonationResponseDTO donationResponseDTO) {
        this.donationResponseDTOList.add(donationResponseDTO);
    }
}
