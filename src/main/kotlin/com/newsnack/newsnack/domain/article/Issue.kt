package com.newsnack.newsnack.domain.article

import com.newsnack.newsnack.domain.category.Category
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "issue")
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "issue_title", length = 255)
    val issueTitle: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category? = null,

    @Column(name = "batch_time", nullable = false)
    val batchTime: OffsetDateTime,

    @Column(name = "is_processed", nullable = false)
    var isProcessed: Boolean = false
)
