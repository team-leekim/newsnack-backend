package com.newsnack.newsnack.dto.response

import com.newsnack.newsnack.domain.content.ArticleType

data class ArticleFeedResponse(
    val contents: List<ArticleFeedItemResponse>,
    val nextCursor: Long?,
    val hasNext: Boolean
)

data class ArticleFeedItemResponse(
    val id: Long,
    val title: String,
    val contentType: ArticleType,
    val publishedAt: String,
    val imageUrls: List<String>,
    val editor: EditorInfoResponse
)

data class EditorInfoResponse(
    val id: Int,
    val name: String,
    val imageUrl: String?
)
