package com.newsnack.newsnack.domain.editor

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "editor")
class Editor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, unique = true, length = 50)
    val name: String,

    @Column(name = "profile_image_url", columnDefinition = "TEXT")
    val profileImageUrl: String? = null,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val keywords: List<String>? = null,

    @Column(name = "persona_prompt", columnDefinition = "TEXT")
    val personaPrompt: String? = null
)
