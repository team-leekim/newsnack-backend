package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.editor.Editor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EditorRepository : JpaRepository<Editor, Int>
