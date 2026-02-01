package com.newsnack.newsnack.domain.reaction

import com.newsnack.newsnack.domain.content.AiArticle
import com.newsnack.newsnack.global.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "reaction_count")
class ReactionCount(
    @Id
    val articleId: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "article_id")
    val article: AiArticle,

    @Column(name = "total_count") var totalCount: Int = 0,
    @Column(name = "happy_count") var happyCount: Int = 0,
    @Column(name = "surprised_count") var surprisedCount: Int = 0,
    @Column(name = "sad_count") var sadCount: Int = 0,
    @Column(name = "angry_count") var angryCount: Int = 0,
    @Column(name = "empathy_count") var empathyCount: Int = 0
) : BaseEntity() {
    fun addReaction(type: ReactionType) {
        when (type) {
            ReactionType.HAPPY -> happyCount++
            ReactionType.SURPRISED -> surprisedCount++
            ReactionType.SAD -> sadCount++
            ReactionType.ANGRY -> angryCount++
            ReactionType.EMPATHY -> empathyCount++
        }
        totalCount++
    }
}
