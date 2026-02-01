package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.AiArticle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AiArticleRepository : JpaRepository<AiArticle, Long>, AiArticleRepositoryCustom
