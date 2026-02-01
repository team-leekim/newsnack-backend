package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.response.ArticleFeedResponse
import com.newsnack.newsnack.dto.response.CategoryBestResponse
import com.newsnack.newsnack.dto.response.EmotionBestResponse
import com.newsnack.newsnack.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contents")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping("/curations/category-best")
    fun getCategoryBest(): ResponseEntity<List<CategoryBestResponse>> {
        val response = articleService.getCategoryBest()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/curations/emotion-best")
    fun getEmotionBest(): ResponseEntity<List<EmotionBestResponse>> {
        val response = articleService.getEmotionBest()
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getArticles(
        @RequestParam(required = false) cursor: Long?,
        @RequestParam(defaultValue = "10") size: Long,
        @RequestParam(required = false) categoryId: Int?
    ): ResponseEntity<ArticleFeedResponse> {
        val response = articleService.getArticleFeed(cursor, size, categoryId)
        return ResponseEntity.ok(response)
    }
}
