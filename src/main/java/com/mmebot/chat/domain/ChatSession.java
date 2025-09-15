package com.mmebot.chat.domain;

import com.mmebot.diary.domain.Diary;
import com.mmebot.bot.domain.Bot;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "chat_session")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 일기와 1:1, DB에서 UNIQUE 제약
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diary_id", nullable = false, unique = true)
    private Diary diary;

    // 세션을 담당하는 봇
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bot_id", nullable = false)
    private Bot bot;

    @Column(length = 32, nullable = false)
    private ChatSessionStatus status;        // 'pending','active',...

    @Column(nullable = false)
    private Integer sendCount = 0;

    @Column(columnDefinition = "text")
    private String summary;

    @CreationTimestamp
    @Column(columnDefinition = "timestamptz",
            nullable = false,
            updatable = false)
    private OffsetDateTime createdAt;
    private OffsetDateTime completedAt;
}