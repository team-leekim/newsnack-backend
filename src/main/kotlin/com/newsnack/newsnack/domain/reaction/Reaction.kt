package com.newsnack.newsnack.domain.reaction

import com.newsnack.newsnack.domain.content.AiArticle
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "reaction")
class Reaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_article_id", nullable = false)
    val aiArticle: AiArticle,

    @Column(name = "reaction_type", nullable = false, length = 20)
    val reactionType: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now()
)
