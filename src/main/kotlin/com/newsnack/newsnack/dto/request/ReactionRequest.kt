package com.newsnack.newsnack.dto.request

import com.newsnack.newsnack.domain.reaction.ReactionType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "감정표현 요청 DTO")
data class ReactionRequest(
    @Schema(description = "감정 타입 (HAPPY, SURPRISED, SAD, ANGRY, EMPATHY)", example = "HAPPY")
    val type: ReactionType
)
