package com.newsnack.newsnack.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(description = "오늘의 뉴스낵 응답 DTO")
data class TodayNewsnackResponse(
    @Schema(description = "오디오 URL")
    val audioUrl: String,
    @Schema(description = "스크립트 목록")
    val script: List<NewsnackScriptResponse>,
    @Schema(description = "기사 목록")
    val articles: List<NewsnackArticleResponse>
)

@Schema(description = "오늘의 뉴스낵 스크립트 응답 DTO")
data class NewsnackScriptResponse(
    @Schema(description = "시작 시간", example = "0.0")
    val startTime: Double,
    @Schema(description = "종료 시간", example = "16.25")
    val endTime: Double,
    @Schema(description = "타이틀", example = "뉴스 제목 1")
    val title: String
)

@Schema(description = "오늘의 뉴스낵 기사 응답 DTO")
data class NewsnackArticleResponse(
    @Schema(description = "기사 ID", example = "501")
    val id: Long,
    @Schema(description = "기사 제목", example = "엔비디아 역대급 실적 발표")
    val title: String,
    @Schema(description = "이미지 URL")
    val imageUrl: String,
    @Schema(description = "발행 일시")
    val publishedAt: Instant
)
