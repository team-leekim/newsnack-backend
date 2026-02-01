package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.response.TodayNewsnackResponse
import com.newsnack.newsnack.service.NewsnackService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsnackController(private val newsnackService: NewsnackService) {

    @GetMapping("/today-newsnack")
    fun getTodayNewsnack(): ResponseEntity<TodayNewsnackResponse> {
        val response = newsnackService.getTodayNewsnack()
        return ResponseEntity.ok(response)
    }
}
