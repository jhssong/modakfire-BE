package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.Donation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FastDonationResponseDTO {
    private List<DonationResponseDTO> donationResponseDTOList = new ArrayList<>();

    public void addDonationResponseDTO(DonationResponseDTO donationResponseDTO) {
        this.donationResponseDTOList.add(donationResponseDTO);
    }
}
