package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.domain.reaction.ReactionType

interface AiArticleRepositoryCustom {
    // 무한 스크롤 피드 조회
    fun findAllByCursor(cursor: Long?, limit: Long, categoryId: Int?): List<AiArticle>

    // 감정별 베스트 1건 조회
    fun findBestByEmotion(emotionType: ReactionType): AiArticle?

    // 카테고리별 베스트 1건 조회
    fun findBestByCategory(categoryId: Int): AiArticle?
}
