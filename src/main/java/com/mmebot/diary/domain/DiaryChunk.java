package com.mmebot.diary.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "diary_chunk")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryChunk {

    /**
     * (diaryId, chunkIndex) index
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diary_id", nullable = false)
    private Diary diary;

    @Column(name = "chunk_index", nullable = false)
    private Integer chunkIndex;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "token_count")
    private Integer tokenCount;

    @CreationTimestamp
    @Column(columnDefinition = "timestamptz",
            nullable = false,
            updatable = false)
    private OffsetDateTime createdAt;

    @OneToOne(mappedBy = "chunk", cascade = CascadeType.ALL, orphanRemoval = true)
    private DiaryChunkEmbedding embedding;
}
