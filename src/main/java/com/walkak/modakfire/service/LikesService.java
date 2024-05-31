package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Likes;
import com.walkak.modakfire.domain.Member;
import com.walkak.modakfire.repository.CenterRepository;
import com.walkak.modakfire.repository.LikesRepository;
import com.walkak.modakfire.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final CenterRepository centerRepository;

    public Long getLikesCountByCenterId(Long centerId){
        return likesRepository.countByCenterId(centerId);
    }

    public boolean isLikeByCenterIdAndMemberId(Long centerId, String memberId) {
        System.out.println(likesRepository.existsByCenterIdAndMemberId(centerId,memberId));
        return likesRepository.existsByCenterIdAndMemberId(centerId,memberId);
    }

    @Transactional
    public String createLikes(Long centerId, String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        Center center = centerRepository.findById(centerId).orElseThrow();
        Likes likes = new Likes(member,center);
        likesRepository.save(likes);
        return "Created";
    }

    @Transactional
    public String deleteLikes(Long centerId, String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        Center center = centerRepository.findById(centerId).orElseThrow();
        Likes likes = likesRepository.findByMemberAndCenter(member,center).orElseThrow();
        likesRepository.delete(likes);
        return "Deleted";
    }

}
