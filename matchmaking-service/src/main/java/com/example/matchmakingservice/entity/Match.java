package com.example.matchmakingservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matches", uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1_id", nullable = false)
    private String user1Id;

    @Column(name = "user2_id", nullable = false)
    private String user2Id;

    public Match() {}

    public Match(String user1Id, String user2Id) {
        if (user1Id.compareTo(user2Id) < 0) {
            this.user1Id = user1Id;
            this.user2Id = user2Id;
        } else {
            this.user1Id = user2Id;
            this.user2Id = user1Id;
        }
    }
}

