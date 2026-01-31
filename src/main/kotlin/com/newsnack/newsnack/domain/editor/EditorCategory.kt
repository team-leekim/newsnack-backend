package com.newsnack.newsnack.domain.editor

import com.newsnack.newsnack.domain.category.Category
import jakarta.persistence.*

@Entity
@Table(name = "editor_category", uniqueConstraints = [UniqueConstraint(columnNames = ["editor_id", "category_id"])])
class EditorCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id", nullable = false)
    val editor: Editor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category
)
