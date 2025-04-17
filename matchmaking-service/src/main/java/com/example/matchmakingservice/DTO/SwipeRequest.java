package com.example.matchmakingservice.DTO;

import com.example.matchmakingservice.enums.SwipeType;
import lombok.Data;

@Data
public class SwipeRequest {
    private String targetUserId;
    private SwipeType swipeType;

}