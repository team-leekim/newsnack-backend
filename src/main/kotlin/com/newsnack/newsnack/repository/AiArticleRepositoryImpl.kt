package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.domain.content.QAiArticle.aiArticle
import com.newsnack.newsnack.domain.reaction.QReactionCount.reactionCount
import com.newsnack.newsnack.domain.reaction.ReactionType
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

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

    override fun findBestByEmotion(emotionType: ReactionType): AiArticle? {
        val orderSpecifier = when (emotionType) {
            ReactionType.HAPPY -> reactionCount.happyCount.desc()
            ReactionType.SURPRISED -> reactionCount.surprisedCount.desc()
            ReactionType.SAD -> reactionCount.sadCount.desc()
            ReactionType.ANGRY -> reactionCount.angryCount.desc()
            ReactionType.EMPATHY -> reactionCount.empathyCount.desc()
        }

        return queryFactory
            .selectFrom(aiArticle)
            .leftJoin(reactionCount).on(aiArticle.id.eq(reactionCount.articleId))
            .orderBy(orderSpecifier, aiArticle.id.desc())
            .fetchFirst()
    }
}
