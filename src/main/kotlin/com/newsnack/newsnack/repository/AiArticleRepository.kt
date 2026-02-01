package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.AiArticle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AiArticleRepository : JpaRepository<AiArticle, Long>, AiArticleRepositoryCustom {
    // 특정 카테고리의 최신 기사 1건 (카테고리 베스트용)
    fun findFirstByCategoryIdOrderByPublishedAtDesc(categoryId: Int): AiArticle?
}
