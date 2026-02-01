package com.newsnack.newsnack.dto.response

data class ArticleSummaryResponse(
    val id: Long,
    val title: String,
    val thumbnailUrl: String?,
    val publishedAt: String,
    val editorName: String,
    val totalReactionCount: Int
)

data class CategoryBestResponse(
    val categoryName: String,
    val article: ArticleSummaryResponse
)

data class EmotionBestResponse(
    val emotionType: String,
    val article: ArticleSummaryResponse
)

data class ArticleFeedResponse(
    val contents: List<ArticleSummaryResponse>,
    val nextCursor: Long?,
    val hasNext: Boolean
)
