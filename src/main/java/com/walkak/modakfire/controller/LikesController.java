package com.walkak.modakfire.controller;

import com.walkak.modakfire.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@CrossOrigin(origins = {"http://localhost:5173", "https://port-0-modakfire-be-1272llwutmz86.sel5.cloudtype.app/api"})
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{centerId}/{memberId}")
    public String createOrDeleteLike(@PathVariable Long centerId, @PathVariable String memberId){
        if(likesService.isLikeByCenterIdAndMemberId(centerId,memberId)){
            return likesService.deleteLikes(centerId,memberId);
        } else{
            return likesService.createLikes(centerId,memberId);
        }
    }

    @GetMapping("/{centerId}/{memberId}")
    public boolean checkLike(@PathVariable Long centerId, @PathVariable String memberId){
        return likesService.isLikeByCenterIdAndMemberId(centerId, memberId);
    }
}
