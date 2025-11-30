package com.nullnull.portfolio.domain.common

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "is_enabled", nullable = false)
    var isEnabled: Boolean = true

    @PrePersist
    protected fun onCreate(){
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    protected fun onUpdate(){
        updatedAt = LocalDateTime.now()
    }

    fun disable() {
        this.isEnabled = false
    }

    fun enable() {
        this.isEnabled = true
    }
}