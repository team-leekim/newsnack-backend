package com.newsnack.newsnack.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "뉴스 상세 정보 응답 DTO")
data class ArticleDetailResponse(
    @Schema(description = "기사 ID", example = "101")
    val id: Long,
    @Schema(description = "기사 제목", example = "기사 제목")
    val title: String,
    @Schema(description = "카테고리", example = "IT")
    val category: String,
    @Schema(description = "발행 일시", example = "2026-01-27 15:30")
    val publishedAt: String,
    @Schema(description = "콘텐츠 타입", example = "WEBTOON")
    val contentType: String,
    @Schema(description = "AI 요약", example = "[\"첫 번째 요약 문장입니다.\", \"두 번째 요약 문장입니다.\", \"세 번째 요약 문장입니다.\"]")
    val summary: List<String>,
    @Schema(description = "본문", example = "AI 에디터가 재작성한 전체 본문 텍스트...")
    val body: String,
    @Schema(description = "에디터 정보")
    val editor: EditorDetailInfoResponse,
    @Schema(description = "이미지 URL 목록", example = "[\"https://example.com/0.jpg\", \"https://example.com/1.jpg\"]")
    val imageUrls: List<String>,
    @Schema(description = "감정 표현 현황")
    val reactionStats: ReactionStatsResponse,
    @Schema(description = "원본 기사 정보 목록")
    val originalArticles: List<OriginArticleResponse>
)

@Schema(description = "에디터 상세 정보 응답 DTO")
data class EditorDetailInfoResponse(
    @Schema(description = "에디터 ID", example = "1")
    val id: Int,
    @Schema(description = "에디터 이름", example = "에디터A")
    val name: String,
    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val imageUrl: String?,
    @Schema(description = "키워드 리스트", example = "[\"#친절한\", \"#IT전문\"]")
    val keywords: List<String>,
    @Schema(description = "에디터 한 줄 소개", example = "친절하게 설명해주는 에디터입니다.")
    val description: String?
)

@Schema(description = "반응 통계 응답 DTO")
data class ReactionStatsResponse(
    @Schema(description = "행복 반응 수", example = "10")
    val happy: Int,
    @Schema(description = "놀람 반응 수", example = "5")
    val surprised: Int,
    @Schema(description = "슬픔 반응 수", example = "2")
    val sad: Int,
    @Schema(description = "분노 반응 수", example = "1")
    val angry: Int,
    @Schema(description = "공감 반응 수", example = "20")
    val empathy: Int
)

@Schema(description = "원본 기사 정보 응답 DTO")
data class OriginArticleResponse(
    @Schema(description = "원본 기사 제목", example = "제목 예시입니다.")
    val title: String,
    @Schema(description = "원본 기사 URL", example = "https://news.com/1")
    val url: String
)
