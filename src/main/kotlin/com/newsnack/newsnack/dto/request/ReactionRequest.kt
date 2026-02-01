package com.newsnack.newsnack.dto.request

import com.newsnack.newsnack.domain.reaction.ReactionType

data class ReactionRequest(
    val type: ReactionType
)
