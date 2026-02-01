package com.newsnack.newsnack.service

import com.newsnack.newsnack.dto.response.CategoryBestResponse
import com.newsnack.newsnack.dto.response.NewsnackArticleResponse
import com.newsnack.newsnack.dto.response.NewsnackScriptResponse
import com.newsnack.newsnack.dto.response.TodayNewsnackResponse
import com.newsnack.newsnack.global.exception.content.TodayNewsnackNotFoundException
import com.newsnack.newsnack.repository.TodayNewsnackRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NewsnackService(private val todayNewsnackRepository: TodayNewsnackRepository) {
    fun getTodayNewsnack(): TodayNewsnackResponse {
        val latest = todayNewsnackRepository.findFirstByOrderByPublishedAtDesc()
            ?: throw TodayNewsnackNotFoundException()

        return TodayNewsnackResponse(
            audioUrl = latest.audioUrl,
            script = latest.briefingArticles.map { NewsnackScriptResponse(it.startTime, it.endTime, it.title) },
            articles = latest.briefingArticles.map {
                NewsnackArticleResponse(
                    it.articleId,
                    it.title,
                    it.thumbnailUrl,
                    latest.publishedAt!!
                )
            }
        )
    }
}
