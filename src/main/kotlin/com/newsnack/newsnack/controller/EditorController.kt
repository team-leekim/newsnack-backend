package com.newsnack.newsnack.controller

import com.newsnack.newsnack.dto.response.EditorDetailResponse
import com.newsnack.newsnack.service.EditorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/editors")
class EditorController(private val editorService: EditorService) {

    @GetMapping("/{id}")
    fun getEditorDetail(@PathVariable id: Int): ResponseEntity<EditorDetailResponse> {
        return ResponseEntity.ok(editorService.getEditorDetail(id))
    }
}
