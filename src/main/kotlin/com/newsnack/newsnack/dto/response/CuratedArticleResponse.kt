package com.newsnack.newsnack.dto.response

data class ArticleSummaryResponse(
    val id: Long,
    val title: String,
    val thumbnailUrl: String?,
    val publishedAt: String? = null,
    val editorName: String? = null,
    )

data class CategoryBestResponse(
    val categoryName: String,
    val article: ArticleSummaryResponse
)

data class EmotionBestResponse(
    val emotionType: String,
    val article: ArticleSummaryResponse
)
