package com.example.matchmakingservice.controller;

import com.example.matchmakingservice.DTO.MatchResponse;
import com.example.matchmakingservice.DTO.SwipeRequest;
import com.example.matchmakingservice.service.MatchmakingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matchmaking")
public class MatchmakingController {

    private final MatchmakingService matchmakingService;

    public MatchmakingController(MatchmakingService matchmakingService) {
        this.matchmakingService = matchmakingService;
    }

    @PostMapping("/{userId}/swipe")
    public ResponseEntity<Void> swipe(
            @PathVariable String userId,
            @RequestBody SwipeRequest swipeRequest) {

        matchmakingService.swipe(userId, swipeRequest.getTargetUserId(), swipeRequest.getSwipeType());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/matches")
    public ResponseEntity<MatchResponse> getMatches(@PathVariable String userId) {
        return ResponseEntity.ok(new MatchResponse(matchmakingService.getMatches(userId)));
    }
}
