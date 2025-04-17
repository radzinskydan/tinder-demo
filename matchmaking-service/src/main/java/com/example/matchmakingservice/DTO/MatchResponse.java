package com.example.matchmakingservice.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class MatchResponse {
    private Set<String> matches;

    public MatchResponse(Set<String> matches) {
        this.matches = matches;
    }
}
