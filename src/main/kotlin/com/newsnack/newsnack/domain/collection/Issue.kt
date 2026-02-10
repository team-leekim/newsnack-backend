package com.newsnack.newsnack.domain.collection

import com.newsnack.newsnack.domain.category.Category
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "issue")
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "title", length = 255)
    val title: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category? = null,

    @Column(name = "batch_time", nullable = false)
    val batchTime: Instant,

    @Enumerated(EnumType.STRING)
    @Column(name = "processing_status", nullable = false, length = 20)
    var processingStatus: ProcessingStatus = ProcessingStatus.PENDING
)
