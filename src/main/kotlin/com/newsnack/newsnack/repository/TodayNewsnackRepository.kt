package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.content.TodayNewsnack
import org.springframework.data.jpa.repository.JpaRepository

interface TodayNewsnackRepository : JpaRepository<TodayNewsnack, Long> {
    fun findFirstByOrderByPublishedAtDesc(): TodayNewsnack?
}
