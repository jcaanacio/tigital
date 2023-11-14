package com.accenture.tigital.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import java.time.Instant;
import java.sql.Timestamp;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@Entity(name = "raffles")
public class Raffle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raffle_id")
    private Long raffleId;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public Raffle(User user, String category) {
        this.user = user;
        this.category = category;
    }

    public Long getRaffleId() {
        return this.raffleId;
    }

    public void setRaffleId(Long raffleId) {
        this.raffleId = raffleId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
