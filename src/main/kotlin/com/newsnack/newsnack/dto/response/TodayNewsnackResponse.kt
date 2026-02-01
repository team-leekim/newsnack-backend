package com.newsnack.newsnack.dto.response

import java.time.OffsetDateTime

data class TodayNewsnackResponse(
    val audioUrl: String,
    val script: List<NewsnackScriptResponse>,
    val articles: List<NewsnackArticleResponse>
)

data class NewsnackScriptResponse(
    val startTime: Double,
    val endTime: Double,
    val title: String
)

data class NewsnackArticleResponse(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val publishedAt: OffsetDateTime
)
