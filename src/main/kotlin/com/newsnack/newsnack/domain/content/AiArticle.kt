package com.newsnack.newsnack.domain.content

import com.fasterxml.jackson.annotation.JsonProperty
import com.newsnack.newsnack.domain.category.Category
import com.newsnack.newsnack.domain.editor.Editor
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.io.Serializable
import java.time.OffsetDateTime

@Entity
@Table(name = "ai_article")
class AiArticle(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "issue_id")
    val issueId: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    val contentType: ArticleType,

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
    val imageData: ImageData? = null,

    @Type(JsonType::class)
    @Column(name = "origin_articles", columnDefinition = "jsonb")
    val originArticles: List<OriginArticle>? = null,

    @Column(name = "published_at")
    val publishedAt: OffsetDateTime? = null
)

data class ImageData(
    @JsonProperty("image_urls")
    val imageUrls: List<String>
) : Serializable

data class OriginArticle(
    val title: String,
    val url: String
): Serializable
