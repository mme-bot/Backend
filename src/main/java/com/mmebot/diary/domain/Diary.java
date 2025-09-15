package com.mmebot.diary.domain;

import com.mmebot.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOT NULL, ON DELETE CASCADE (DB 레벨)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private DiaryEmotion emotion;

    @Column(columnDefinition = "text")
    private String summaryShort;

    @Column(nullable = false)
    private LocalDate date;   // 하루 1개 가정

    @CreationTimestamp
    @Column(columnDefinition = "timestamptz",
            nullable = false,
            updatable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    private OffsetDateTime deletedAt;

    @Builder.Default
    @OneToMany(mappedBy = "diary",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DiaryChunk> chunks = new ArrayList<>();

    @PreUpdate
    void touchUpdatedAt() { this.updatedAt = OffsetDateTime.now(); }
}
