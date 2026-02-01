package com.newsnack.newsnack.global.common

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()"
    )
    var createdAt: Instant = Instant.now()
        protected set

    @LastModifiedDate
    @Column(
        nullable = false,
        columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()"
    )
    var updatedAt: Instant = Instant.now()
        protected set
}
