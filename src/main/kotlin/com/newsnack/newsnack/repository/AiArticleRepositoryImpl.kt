package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.domain.content.QAiArticle.aiArticle
import com.newsnack.newsnack.domain.reaction.QReactionCount.reactionCount
import com.newsnack.newsnack.domain.reaction.ReactionType
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class AiArticleRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : AiArticleRepositoryCustom {

    override fun findAllByCursor(cursor: Long?, limit: Long, categoryId: Int?): List<AiArticle> {
        return queryFactory
            .selectFrom(aiArticle)
            .where(
                cursorLt(cursor),
                categoryEq(categoryId)
            )
            .orderBy(aiArticle.id.desc())
            .limit(limit + 1)
            .fetch()
    }

    private fun cursorLt(cursor: Long?): BooleanExpression? =
        cursor?.let { aiArticle.id.lt(it) }

    private fun categoryEq(categoryId: Int?): BooleanExpression? =
        categoryId?.let { aiArticle.category.id.eq(it) }

    override fun findBestByCategory(categoryId: Int): AiArticle? {
        val now = OffsetDateTime.now()

        // 1. 최근 24시간 내 인기순 조회
        val bestIn24h = queryFactory.selectFrom(aiArticle)
            .leftJoin(reactionCount).on(aiArticle.id.eq(reactionCount.articleId))
            .where(
                aiArticle.category.id.eq(categoryId),
                aiArticle.publishedAt.after(now.minusDays(1))
            )
            .orderBy(reactionCount.totalCount.desc(), aiArticle.id.desc())
            .fetchFirst()

        if (bestIn24h != null) return bestIn24h

        // 2. 폴백: 해당 카테고리 최신순 1건
        return queryFactory.selectFrom(aiArticle)
            .where(aiArticle.category.id.eq(categoryId))
            .orderBy(aiArticle.publishedAt.desc())
            .fetchFirst()
    }

    override fun findBestByEmotion(emotionType: ReactionType): AiArticle? {
        val now = OffsetDateTime.now()
        val oneDayAgo = now.minusDays(1)

        val orderSpecifier = when (emotionType) {
            ReactionType.HAPPY -> reactionCount.happyCount.desc()
            ReactionType.SURPRISED -> reactionCount.surprisedCount.desc()
            ReactionType.SAD -> reactionCount.sadCount.desc()
            ReactionType.ANGRY -> reactionCount.angryCount.desc()
            ReactionType.EMPATHY -> reactionCount.empathyCount.desc()
        }

        // 1. 최근 24시간 내 발행된 기사 중 해당 감정 점수가 가장 높은 기사 조회
        val bestIn24h = queryFactory.selectFrom(aiArticle)
            .leftJoin(reactionCount).on(aiArticle.id.eq(reactionCount.articleId))
            .leftJoin(aiArticle.category).fetchJoin()
            .leftJoin(aiArticle.editor).fetchJoin()
            .where(
                aiArticle.publishedAt.after(oneDayAgo)
            )
            .orderBy(orderSpecifier, aiArticle.id.desc())
            .fetchFirst()

        if (bestIn24h != null) return bestIn24h

        // 2. 폴백: 최근 24시간 내 기사가 없으면, 전체 기간 중 해당 감정 점수가 가장 높은 기사 1건 조회
        return queryFactory.selectFrom(aiArticle)
            .leftJoin(reactionCount).on(aiArticle.id.eq(reactionCount.articleId))
            .leftJoin(aiArticle.category).fetchJoin()
            .leftJoin(aiArticle.editor).fetchJoin()
            .orderBy(orderSpecifier, aiArticle.id.desc())
            .fetchFirst()
    }
}
