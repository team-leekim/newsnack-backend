package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.reaction.ReactionCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReactionCountRepository : JpaRepository<ReactionCount, Long> {
    fun findByArticleId(articleId: Long): ReactionCount?
}
