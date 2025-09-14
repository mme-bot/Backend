package com.mmebot.users.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "sns_users")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SnsUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOT NULL, ON DELETE CASCADE (DB 레벨)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @Column(length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private SnsProvider provider;      // ex) kakao

    @Column(length = 128, nullable = false)
    private String providerUid;   // SNS 고유 ID

    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}

