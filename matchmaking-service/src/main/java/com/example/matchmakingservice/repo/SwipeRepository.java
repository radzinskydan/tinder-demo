package com.example.matchmakingservice.repo;

import com.example.matchmakingservice.entity.Swipe;
import com.example.matchmakingservice.enums.SwipeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    Optional<Swipe> findBySwiperIdAndTargetId(String swiperId, String targetId);
    List<Swipe> findBySwiperIdAndSwipeType(String swiperId, SwipeType swipeType);
}
