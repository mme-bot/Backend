package com.mmebot.bot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bot_image")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BotImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOT NULL, ON DELETE CASCADE (DB 레벨)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bot_id", nullable = false)
    private Bot bot;

    @Column(length = 32, nullable = false)
    private String mood; // 'neutral','happy','sad','angry' 등

    @Column(columnDefinition = "text", nullable = false)
    private String url;
}
