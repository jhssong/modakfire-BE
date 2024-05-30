package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Member;
import com.walkak.modakfire.dto.MemberRequestDTO;
import com.walkak.modakfire.dto.MemberResponseDTO;
import com.walkak.modakfire.dto.MemberUpdateRequestDTO;
import com.walkak.modakfire.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {
        String id = memberRequestDTO.getId();
        String name = memberRequestDTO.getName();
        String email = memberRequestDTO.getEmail();
        String registerDate = memberRequestDTO.getRegisterDate();

        Member member = new Member(id, name, email, registerDate);
        System.out.println(member);
        memberRepository.save(member);

        return member.translate();
    }

    public MemberResponseDTO getMemberById(String id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return member.translate();
    }

    public Member getMemberEntityById(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public MemberResponseDTO updateMemberById(String id, MemberUpdateRequestDTO memberRequestDTO) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setName(memberRequestDTO.getName());
        member.setEmail(memberRequestDTO.getEmail());
        memberRepository.save(member);
        return member.translate();
    }

    public String deleteMemberById(String id) {
        try {
            Member member = memberRepository.findById(id).orElseThrow();
            memberRepository.delete(member);
            return "DELETED";
        } catch (Exception e) {
            return "FAILED";
        }
    }
}
