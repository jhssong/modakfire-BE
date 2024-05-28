package com.walkak.modakfire.domain;

import com.walkak.modakfire.domain.EnumType.MemberRank;
import com.walkak.modakfire.dto.MemberResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
    private MemberRank memberRank;
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Like> likes;

    public Member(Long id, String name, String email, String registerDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.memberRank = MemberRank.EMBER;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        this.registerDate = LocalDateTime.parse(registerDate, formatter);
    }

    /*@OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Donation> donations;*/

    /*@OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<PeriodicalDonation> periodicalDonations;*/

    public MemberResponseDTO translate(){
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.update(this);
        return memberResponseDTO;
    }

}
