package com.newsnack.newsnack.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "기사 요약 정보 응답 DTO")
data class ArticleSummaryResponse(
    @Schema(description = "기사 ID", example = "101")
    val id: Long,
    @Schema(description = "기사 제목", example = "엔비디아 실적 발표, 주가 급등")
    val title: String,
    @Schema(description = "썸네일 이미지 URL")
    val thumbnailUrl: String?,
    @Schema(description = "발행 일시", example = "2026-01-27 10:00")
    val publishedAt: String? = null,
    @Schema(description = "에디터 이름", example = "에디터A")
    val editorName: String? = null,
    )

@Schema(description = "분야별 베스트 뉴스 응답 DTO")
data class CategoryBestResponse(
    @Schema(description = "카테고리명", example = "경제")
    val categoryName: String,
    @Schema(description = "기사 정보")
    val article: ArticleSummaryResponse
)

@Schema(description = "감정별 베스트 뉴스 응답 DTO")
data class EmotionBestResponse(
    @Schema(description = "감정 타입", example = "ANGRY")
    val emotionType: String,
    @Schema(description = "기사 정보")
    val article: ArticleSummaryResponse
)
