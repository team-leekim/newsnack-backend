package com.newsnack.newsnack.dto.response

data class ArticleDetailResponse(
    val id: Long,
    val title: String,
    val category: String,
    val publishedAt: String,
    val contentType: String,
    val summary: List<String>,
    val body: String,
    val editor: EditorDetailInfoResponse,
    val imageUrls: List<String>,
    val reactionStats: ReactionStatsResponse,
    val originalArticles: List<OriginArticleResponse>
)

data class EditorDetailInfoResponse(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val keywords: List<String>,
    val description: String?
)

data class ReactionStatsResponse(
    val happy: Int,
    val surprised: Int,
    val sad: Int,
    val angry: Int,
    val empathy: Int
)

data class OriginArticleResponse(
    val title: String,
    val url: String
)
