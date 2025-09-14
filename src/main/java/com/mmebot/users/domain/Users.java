package com.mmebot.users.domain;

import com.mmebot.bot.domain.Bot;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: TRUE (DDL상 필수 아님)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "bot_id")
    private Bot bot;

    @Column(length = 320, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Boolean isSns = Boolean.FALSE;

    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    private OffsetDateTime deletedAt;

    @PreUpdate
    void touchUpdatedAt() { this.updatedAt = OffsetDateTime.now(); }
}

