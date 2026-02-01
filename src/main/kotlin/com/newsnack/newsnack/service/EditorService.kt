package com.newsnack.newsnack.service

import com.newsnack.newsnack.dto.response.EditorDetailResponse
import com.newsnack.newsnack.dto.response.EditorRecentNewsResponse
import com.newsnack.newsnack.global.exception.content.EditorNotFoundException
import com.newsnack.newsnack.repository.AiArticleRepository
import com.newsnack.newsnack.repository.EditorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
@Transactional(readOnly = true)
class EditorService(
    private val editorRepository: EditorRepository,
    private val aiArticleRepository: AiArticleRepository
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.of("UTC"))
    }

    fun getEditorDetail(id: Int): EditorDetailResponse {
        val editor = editorRepository.findById(id).orElseThrow { EditorNotFoundException() }
        val recentArticles = aiArticleRepository.findTop10ByEditorIdOrderByPublishedAtDesc(id)

        return EditorDetailResponse(
            id = editor.id,
            name = editor.name,
            description = editor.description,
            imageUrl = editor.imageUrl,
            keywords = editor.keywords ?: emptyList(),
            recentNews = recentArticles.map { article ->
                EditorRecentNewsResponse(
                    id = article.id,
                    title = article.title,
                    thumbnailUrl = article.thumbnailUrl,
                    publishedAt = formatter.format(article.publishedAt)
                )
            }
        )
    }
}
