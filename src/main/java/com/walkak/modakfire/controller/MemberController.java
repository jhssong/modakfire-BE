package com.walkak.modakfire.controller;

import com.walkak.modakfire.dto.MemberRequestDTO;
import com.walkak.modakfire.dto.MemberResponseDTO;
import com.walkak.modakfire.dto.MemberUpdateRequestDTO;
import com.walkak.modakfire.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public MemberResponseDTO createMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        return memberService.createMember(memberRequestDTO);
    }

    @GetMapping("/{id}")
    public MemberResponseDTO getMember(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    public MemberResponseDTO updateMember(@PathVariable String id, @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO) {
        return memberService.updateMemberById(id, memberUpdateRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable String id) {
        return memberService.deleteMemberById(id);
    }
}
