package com.newsnack.newsnack.domain.content

import com.newsnack.newsnack.domain.category.Category
import com.newsnack.newsnack.domain.editor.Editor
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.OffsetDateTime

@Entity
@Table(name = "ai_article")
class AiArticle(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "issue_id")
    val issueId: Long? = null,

    @Column(name = "content_type", nullable = false)
    val contentType: String, // WEBTOON, CARD_NEWS

    @Column(nullable = false)
    val title: String,

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    val thumbnailUrl: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    val editor: Editor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val summary: List<String>? = null,

    @Column(columnDefinition = "TEXT")
    val body: String? = null,

    @Type(JsonType::class)
    @Column(name = "image_data", columnDefinition = "jsonb")
    val imageData: Map<String, Any>? = null,

    @Type(JsonType::class)
    @Column(name = "origin_articles", columnDefinition = "jsonb")
    val originArticles: List<Map<String, String>>? = null,

    @Column(name = "published_at")
    val publishedAt: OffsetDateTime? = null
)
