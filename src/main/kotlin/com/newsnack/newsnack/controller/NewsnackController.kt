package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.response.TodayNewsnackResponse
import com.newsnack.newsnack.service.NewsnackService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Newsnack", description = "Today's Newsnack API")
class NewsnackController(private val newsnackService: NewsnackService) {

    @GetMapping("/today-newsnack")
    @Operation(summary = "오늘의 뉴스낵 조회", description = "매일 2회(아침/저녁) 배포되는 AI 앵커의 브리핑 콘텐츠를 조회합니다.")
    fun getTodayNewsnack(): ResponseEntity<TodayNewsnackResponse> {
        val response = newsnackService.getTodayNewsnack()
        return ResponseEntity.ok(response)
    }
}
