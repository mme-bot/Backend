package com.mmebot.users.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "auth_token")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOT NULL, ON DELETE CASCADE (DB 레벨)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthTokenType type;

    @Column(nullable = false)
    private OffsetDateTime issuedAt = OffsetDateTime.now();

    @Column(nullable = false)
    private OffsetDateTime expiredAt;

    private OffsetDateTime revokedAt;

    @Column(columnDefinition = "text")
    private String userAgent;

    // PostgreSQL 'inet' 타입 활용
    @Column(columnDefinition = "inet")
    private String ipAddress;
}
