package com.duoroute.backend.domain.couple.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "couples")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String status; // ACTIVE(연결됨), BROKEN(헤어짐)

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Couple() {
        this.status = "ACTIVE";
        this.createdAt = LocalDateTime.now();
    }
}
