package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.request.ReactionRequest
import com.newsnack.newsnack.dto.response.ArticleDetailResponse
import com.newsnack.newsnack.dto.response.ArticleFeedResponse
import com.newsnack.newsnack.dto.response.CategoryBestResponse
import com.newsnack.newsnack.dto.response.EmotionBestResponse
import com.newsnack.newsnack.service.ArticleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contents")
@Tag(name = "Article", description = "Article API")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping("/curations/category-best")
    @Operation(summary = "분야별 베스트 뉴스 조회", description = "각 카테고리(IT, 경제, 사회 등) 별로 가장 반응이 뜨거운 뉴스 1개씩 조회합니다.")
    fun getCategoryBest(): ResponseEntity<List<CategoryBestResponse>> {
        val response = articleService.getCategoryBest()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/curations/emotion-best")
    @Operation(summary = "감정별 베스트 뉴스 조회", description = "5가지 감정(행복, 분노, 슬픔, 놀람, 공감) 별로 가장 해당 감정 점수가 높은 뉴스 1개씩 조회합니다.")
    fun getEmotionBest(): ResponseEntity<List<EmotionBestResponse>> {
        val response = articleService.getEmotionBest()
        return ResponseEntity.ok(response)
    }

    @GetMapping
    @Operation(summary = "무한 뉴스 피드 조회", description = "전체 AI 뉴스 콘텐츠(웹툰/카드뉴스)를 최신순으로 끊김 없이 조회합니다.")
    fun getArticles(
        @RequestParam(required = false) cursor: Long?,
        @RequestParam(defaultValue = "5") size: Long,
        @RequestParam(required = false) categoryId: Int?
    ): ResponseEntity<ArticleFeedResponse> {
        val response = articleService.getArticleFeed(cursor, size, categoryId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    @Operation(summary = "뉴스 상세 정보 조회", description = "특정 뉴스의 전체 내용(웹툰/카드뉴스 이미지, 요약, 본문)을 조회합니다.")
    fun getArticleDetail(@PathVariable id: Long): ResponseEntity<ArticleDetailResponse> {
        val response = articleService.getArticleDetail(id)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/reactions")
    @Operation(summary = "감정표현 등록", description = "뉴스에 대한 감정 표현을 등록합니다. 중복 클릭을 허용합니다.")
    fun addReaction(
        @PathVariable id: Long,
        @RequestBody request: ReactionRequest
    ): ResponseEntity<Unit> {
        articleService.addReaction(id, request)
        return ResponseEntity.ok().build()
    }
}
