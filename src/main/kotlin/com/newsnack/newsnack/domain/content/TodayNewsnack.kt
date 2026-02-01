package com.newsnack.newsnack.domain.content

import com.fasterxml.jackson.annotation.JsonProperty
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

    @Column(
        name = "published_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()"
    )
    val publishedAt: OffsetDateTime = OffsetDateTime.now()
)

data class BriefingArticle(
    @JsonProperty("article_id")
    val articleId: Long,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("thumbnail_url")
    val thumbnailUrl: String,

    @JsonProperty("start_time")
    val startTime: Double,

    @JsonProperty("end_time")
    val endTime: Double
)
