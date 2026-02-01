package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.reaction.Reaction
import org.springframework.data.jpa.repository.JpaRepository

interface ReactionRepository : JpaRepository<Reaction, Long>
