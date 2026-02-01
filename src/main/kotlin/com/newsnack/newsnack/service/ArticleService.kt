package com.newsnack.newsnack.service

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.domain.reaction.ReactionType
import com.newsnack.newsnack.dto.response.*
import com.newsnack.newsnack.repository.AiArticleRepository
import com.newsnack.newsnack.repository.CategoryRepository
import com.newsnack.newsnack.repository.ReactionCountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

@Service
@Transactional(readOnly = true)
class ArticleService(
    private val aiArticleRepository: AiArticleRepository,
    private val categoryRepository: CategoryRepository,
    private val reactionCountRepository: ReactionCountRepository,
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    }

    fun getCategoryBest(): List<CategoryBestResponse> {
        return categoryRepository.findAll().mapNotNull { category ->
            aiArticleRepository.findBestByCategory(category.id)?.let { article ->
                CategoryBestResponse(category.name, article.toSummaryResponseForCategoryBest())
            }
        }
    }

    fun getEmotionBest(): List<EmotionBestResponse> {
        return ReactionType.entries.mapNotNull { emotion ->
            aiArticleRepository.findBestByEmotion(emotion)?.let { article ->
                EmotionBestResponse(emotion.name, article.toSummaryResponseForEmotionBest())
            }
        }
    }

    fun getArticleFeed(cursor: Long?, size: Long, categoryId: Int?): ArticleFeedResponse {
        val articles = aiArticleRepository.findAllByCursor(cursor, size, categoryId)
        val hasNext = articles.size > size
        val content = if (hasNext) articles.dropLast(1) else articles

        return ArticleFeedResponse(
            contents = content.map { it.toArticleFeedItemResponse() },
            nextCursor = if (hasNext) content.last().id else null,
            hasNext = hasNext
        )
    }

    // CategoryBest 용 변환 함수
    private fun AiArticle.toSummaryResponseForCategoryBest() = ArticleSummaryResponse(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl,
        publishedAt = null,
        editorName = null
    )

    // EmotionBest 용 변환 함수
    private fun AiArticle.toSummaryResponseForEmotionBest() = ArticleSummaryResponse(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl,
        publishedAt = this.publishedAt?.format(formatter),
        editorName = this.editor.name
    )

    // ArticleFeed 용 변환 함수
    private fun AiArticle.toArticleFeedItemResponse() = ArticleFeedItemResponse(
        id = this.id,
        title = this.title,
        contentType = this.contentType,
        publishedAt = this.publishedAt?.format(formatter) ?: "",
        imageUrls = this.imageData?.imageUrls ?: emptyList(),
        editor = EditorInfoResponse(
            id = this.editor.id,
            name = this.editor.name,
            imageUrl = this.editor.imageUrl
        )
    )
}
