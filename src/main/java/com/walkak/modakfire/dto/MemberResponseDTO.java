package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.MemberRank;
import com.walkak.modakfire.domain.Like;
import com.walkak.modakfire.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String name;
    private String email;
    private MemberRank memberRank;
    private LocalDateTime registerDate;
    private List<Like> likes;

    public void update(Member member){
        id = member.getId();
        name = member.getName();
        email = member.getEmail();
        memberRank = member.getMemberRank();
        registerDate = member.getRegisterDate();
        likes = member.getLikes();
    }
}
