package com.example.matchmakingservice.service;

import com.example.matchmakingservice.entity.Match;
import com.example.matchmakingservice.entity.Swipe;
import com.example.matchmakingservice.enums.SwipeType;
import com.example.matchmakingservice.repo.MatchRepository;
import com.example.matchmakingservice.repo.SwipeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchmakingService {

    private final SwipeRepository swipeRepository;
    private final MatchRepository matchRepository;

    public MatchmakingService(SwipeRepository swipeRepository, MatchRepository matchRepository) {
        this.swipeRepository = swipeRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void swipe(String swiperId, String targetId, SwipeType swipeType) {
        if (swiperId.equals(targetId)) {
            throw new IllegalArgumentException("You cannot swipe on yourself");
        }

        Swipe swipe = swipeRepository.findBySwiperIdAndTargetId(swiperId, targetId)
                .orElse(new Swipe(swiperId, targetId, swipeType));

        swipe.setSwipeType(swipeType);
        swipeRepository.save(swipe);

        if (swipeType == SwipeType.RIGHT) {
            // Проверяем, есть ли взаимный свайп вправо
            Swipe reciprocal = swipeRepository.findBySwiperIdAndTargetId(targetId, swiperId)
                    .orElse(null);

            if (reciprocal != null && reciprocal.getSwipeType() == SwipeType.RIGHT) {
                createMatch(swiperId, targetId);
            }
        }
    }

    @Transactional
    protected void createMatch(String user1Id, String user2Id) {
        // Упорядочиваем ID для уникальности
        String first = user1Id.compareTo(user2Id) < 0 ? user1Id : user2Id;
        String second = user1Id.compareTo(user2Id) < 0 ? user2Id : user1Id;

        boolean exists = matchRepository.existsByUser1IdAndUser2Id(first, second);
        if (!exists) {
            var match = new Match(first, second);
            matchRepository.save(match);
        }
    }

    @Transactional(readOnly= true)
    public Set<String> getMatches(String userId) {
        List<Match> matches = matchRepository.findByUser1IdOrUser2Id(userId, userId);
        Set<String> matchedUsers = new HashSet<>();

        for (Match m : matches) {
            if (userId.equals(m.getUser1Id())) {
                matchedUsers.add(m.getUser2Id());
            } else {
                matchedUsers.add(m.getUser1Id());
            }
        }

        return matchedUsers;
    }
}
