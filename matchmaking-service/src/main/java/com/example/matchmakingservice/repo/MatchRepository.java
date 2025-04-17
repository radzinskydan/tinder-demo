package com.example.matchmakingservice.repo;

import com.example.matchmakingservice.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    boolean existsByUser1IdAndUser2Id(String user1Id, String user2Id);
    boolean existsByUser2IdAndUser1Id(String user2Id, String user1Id);

    List<Match> findByUser1IdOrUser2Id(String user1Id, String user2Id);
}
