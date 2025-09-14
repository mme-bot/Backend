package com.mmebot.bot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "bot",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_bot_name", columnNames = {"name"})
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,
            nullable = false)
    private String name;

    @Column(columnDefinition = "text",
            nullable = false)
    private String persona;

    @Column(columnDefinition = "text")
    private String script;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    void touchUpdatedAt() { this.updatedAt = OffsetDateTime.now(); }
}
