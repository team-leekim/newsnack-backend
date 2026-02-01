package com.newsnack.newsnack.domain.content

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.OffsetDateTime

@Entity
@Table(name = "today_newsnack")
class TodayNewsnack(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "audio_url", nullable = false, columnDefinition = "TEXT")
    val audioUrl: String,

    @Type(JsonType::class)
    @Column(name = "briefing_articles", nullable = false, columnDefinition = "jsonb")
    val briefingArticles: List<BriefingArticle>,

    @Column(name = "published_at")
    val publishedAt: OffsetDateTime? = null
)

data class BriefingArticle(
    val articleId: Long,
    val title: String,
    val thumbnailUrl: String,
    val startTime: Double,
    val endTime: Double
)
