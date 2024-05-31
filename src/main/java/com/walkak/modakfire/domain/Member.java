package com.walkak.modakfire.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.walkak.modakfire.domain.EnumType.MemberRank;
import com.walkak.modakfire.dto.MemberResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;
    private String name;
    private String email;
    private MemberRank memberRank;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Likes> likes;

    public Member(String id, String name, String email, String registerDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.memberRank = MemberRank.EMBER;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.registerDate = LocalDateTime.parse(registerDate, formatter);
        this.likes = null;
    }
    @Builder
    public Member(String name, String email, MemberRank memberRank, LocalDateTime registerDate, List<Likes> likes) {
        this.name = name;
        this.email = email;
        this.memberRank = memberRank;
        this.registerDate = registerDate;
        this.likes = likes;
    }

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    @ToString.Exclude
    private List<Donation> donations;

    /*@OneToOne(mappedBy = "member")
    @ToString.Exclude
    private List<PeriodicalDonation> periodicalDonations;*/

    public MemberResponseDTO translate(){
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.update(this);
        return memberResponseDTO;
    }

}
