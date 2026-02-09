package com.newsnack.newsnack.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "에디터 상세 및 최근 기사 응답 DTO")
data class EditorDetailResponse(
    @Schema(description = "에디터 ID", example = "1")
    val id: Int,
    @Schema(description = "에디터 이름", example = "에디터A")
    val name: String,
    @Schema(description = "에디터 설명", example = "IT 소식을 가장 쉽게 전달하는 에디터")
    val description: String?,
    @Schema(description = "프로필 이미지 URL")
    val imageUrl: String?,
    @Schema(description = "키워드 리스트", example = "[\"#친절한\", \"#IT전문\"]")
    val keywords: List<String>,
    @Schema(description = "최근 기사 목록")
    val recentNews: List<EditorRecentNewsResponse>
)

@Schema(description = "에디터 최근 기사 응답 DTO")
data class EditorRecentNewsResponse(
    @Schema(description = "기사 ID", example = "101")
    val id: Long,
    @Schema(description = "기사 제목", example = "갤럭시 S26 출시 루머 총정리")
    val title: String,
    @Schema(description = "썸네일 이미지 URL")
    val thumbnailUrl: String?,
    @Schema(description = "발행 일시", example = "2026-01-27 10:00")
    val publishedAt: String
)
