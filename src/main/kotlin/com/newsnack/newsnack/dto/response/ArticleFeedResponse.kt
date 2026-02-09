package com.newsnack.newsnack.dto.response

import com.newsnack.newsnack.domain.content.ArticleType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "뉴스 피드 응답 DTO")
data class ArticleFeedResponse(
    @Schema(description = "기사 목록")
    val contents: List<ArticleFeedItemResponse>,
    @Schema(description = "다음 커서 ID", example = "190")
    val nextCursor: Long?,
    @Schema(description = "다음 페이지 존재 여부", example = "true")
    val hasNext: Boolean
)

@Schema(description = "뉴스 피드 아이템 응답 DTO")
data class ArticleFeedItemResponse(
    @Schema(description = "기사 ID", example = "200")
    val id: Long,
    @Schema(description = "기사 제목", example = "기사 제목")
    val title: String,
    @Schema(description = "콘텐츠 타입", example = "WEBTOON")
    val contentType: ArticleType,
    @Schema(description = "발행 일시", example = "2026-01-27 15:30")
    val publishedAt: String,
    @Schema(description = "이미지 URL 목록", example = "[\"https://example.com/0.jpg\", \"https://example.com/1.jpg\"]")
    val imageUrls: List<String>,
    @Schema(description = "에디터 정보")
    val editor: EditorInfoResponse
)

@Schema(description = "에디터 기본 정보 응답 DTO")
data class EditorInfoResponse(
    @Schema(description = "에디터 ID", example = "2")
    val id: Int,
    @Schema(description = "에디터 이름", example = "에디터B")
    val name: String,
    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val imageUrl: String?
)
