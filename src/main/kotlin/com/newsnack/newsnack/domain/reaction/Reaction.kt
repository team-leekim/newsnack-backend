package com.newsnack.newsnack.domain.reaction

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.global.common.BaseEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "reaction")
class Reaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_article_id", nullable = false)
    val aiArticle: AiArticle,

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_type", nullable = false, length = 20)
    val reactionType: ReactionType,
) : BaseEntity()
