package com.newsnack.newsnack.dto.response

data class EditorDetailResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val keywords: List<String>,
    val recentNews: List<EditorRecentNewsResponse>
)

data class EditorRecentNewsResponse(
    val id: Long,
    val title: String,
    val thumbnailUrl: String?,
    val publishedAt: String
)
