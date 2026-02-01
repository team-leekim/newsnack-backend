package com.newsnack.newsnack.service

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.domain.reaction.Reaction
import com.newsnack.newsnack.domain.reaction.ReactionCount
import com.newsnack.newsnack.domain.reaction.ReactionType
import com.newsnack.newsnack.dto.request.ReactionRequest
import com.newsnack.newsnack.dto.response.*
import com.newsnack.newsnack.global.exception.content.ArticleNotFoundException
import com.newsnack.newsnack.repository.AiArticleRepository
import com.newsnack.newsnack.repository.CategoryRepository
import com.newsnack.newsnack.repository.ReactionCountRepository
import com.newsnack.newsnack.repository.ReactionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
@Transactional(readOnly = true)
class ArticleService(
    private val aiArticleRepository: AiArticleRepository,
    private val categoryRepository: CategoryRepository,
    private val reactionCountRepository: ReactionCountRepository,
    private val reactionRepository: ReactionRepository,
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.of("UTC"))
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

    fun getArticleDetail(id: Long): ArticleDetailResponse {
        val article = aiArticleRepository.findByIdOrNull(id)
            ?: throw ArticleNotFoundException()

        val reactionCount = reactionCountRepository.findByArticleId(id)

        return ArticleDetailResponse(
            id = article.id,
            title = article.title,
            category = article.category.name,
            publishedAt = formatter.format(article.publishedAt),
            contentType = article.contentType.name,
            summary = article.summary ?: emptyList(),
            body = article.body ?: "",
            editor = EditorDetailInfoResponse(
                id = article.editor.id,
                name = article.editor.name,
                imageUrl = article.editor.imageUrl,
                keywords = article.editor.keywords ?: emptyList(),
                description = article.editor.description
            ),
            imageUrls = article.imageData?.imageUrls ?: emptyList(),
            reactionStats = ReactionStatsResponse(
                happy = reactionCount?.happyCount ?: 0,
                surprised = reactionCount?.surprisedCount ?: 0,
                sad = reactionCount?.sadCount ?: 0,
                angry = reactionCount?.angryCount ?: 0,
                empathy = reactionCount?.empathyCount ?: 0
            ),
            originalArticles = article.originArticles?.map {
                OriginArticleResponse(it.title, it.url)
            } ?: emptyList()
        )
    }

    @Transactional
    fun addReaction(articleId: Long, request: ReactionRequest) {
        val article = aiArticleRepository.findByIdOrNull(articleId)
            ?: throw ArticleNotFoundException()

        // 1. 리액션 로그 저장
        reactionRepository.save(
            Reaction(
                aiArticle = article,
                reactionType = request.type
            )
        )

        // 2. 리액션 개수 업데이트
        val reactionCount = reactionCountRepository.findByArticleId(articleId)
            ?: reactionCountRepository.save(ReactionCount(articleId = articleId, article = article))

        reactionCount.addReaction(request.type)
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
        publishedAt = formatter.format(this.publishedAt),
        editorName = this.editor.name
    )

    // ArticleFeed 용 변환 함수
    private fun AiArticle.toArticleFeedItemResponse() = ArticleFeedItemResponse(
        id = this.id,
        title = this.title,
        contentType = this.contentType,
        publishedAt = formatter.format(this.publishedAt),
        imageUrls = this.imageData?.imageUrls ?: emptyList(),
        editor = EditorInfoResponse(
            id = this.editor.id,
            name = this.editor.name,
            imageUrl = this.editor.imageUrl
        )
    )
}
