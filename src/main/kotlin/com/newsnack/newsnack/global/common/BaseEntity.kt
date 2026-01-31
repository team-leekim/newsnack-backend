package com.newsnack.newsnack.global.common

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
        protected set
}
