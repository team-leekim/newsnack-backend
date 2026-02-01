package com.newsnack.newsnack.domain.collection

import com.newsnack.newsnack.domain.category.Category
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "raw_article")
class RawArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false, length = 500)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

    @Column(name = "origin_url", nullable = false, unique = true, columnDefinition = "TEXT")
    val originUrl: String,

    @Column(nullable = false, length = 50)
    val source: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue? = null,

    @Column(name = "published_at", nullable = false)
    val publishedAt: Instant,

    @Column(name = "crawled_at", nullable = false)
    val crawledAt: Instant = Instant.now()
)
