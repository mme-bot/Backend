package com.mmebot.diary.domain;


import com.mmebot.common.converter.PgVectorFloatArrayConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diary_chunk_embedding")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryChunkEmbedding {

    @Id
    @Column(name = "chunk_id")
    private Long chunkId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chunk_id")
    private DiaryChunk chunk;

    // float[] <-> Vector 컨버터
    @Convert(converter = PgVectorFloatArrayConverter.class)
    @Column(name = "embedding",
            columnDefinition = "vector(1536)",
            nullable = false)
    private float[] embedding;
}
