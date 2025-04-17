package com.example.matchmakingservice.entity;

import com.example.matchmakingservice.enums.SwipeType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "swipes", uniqueConstraints = @UniqueConstraint(columnNames = {"swiper_id", "target_id"}))
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "swiper_id", nullable = false)
    private String swiperId;

    @Column(name = "target_id", nullable = false)
    private String targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SwipeType swipeType;

    public Swipe() {}

    public Swipe(String swiperId, String targetId, SwipeType swipeType) {
        this.swiperId = swiperId;
        this.targetId = targetId;
        this.swipeType = swipeType;
    }
}
