package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.response.EditorDetailResponse
import com.newsnack.newsnack.service.EditorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/editors")
@Tag(name = "Editor", description = "Editor API")
class EditorController(private val editorService: EditorService) {

    @GetMapping("/{id}")
    @Operation(summary = "에디터 상세 정보 조회", description = "AI 에디터의 프로필과 해당 에디터가 작성한 최근 기사들을 조회합니다.")
    fun getEditorDetail(@PathVariable id: Int): ResponseEntity<EditorDetailResponse> {
        return ResponseEntity.ok(editorService.getEditorDetail(id))
    }
}
