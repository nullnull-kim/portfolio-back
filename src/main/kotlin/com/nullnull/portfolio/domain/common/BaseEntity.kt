package com.nullnull.portfolio.domain.common

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "created", nullable = false, updatable = false)
    var created: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "updated", nullable = false)
    var updated: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = true

    @PrePersist
    protected fun onCreate(){
        val now = LocalDateTime.now()
        created = now
        updated = now
    }

    @PreUpdate
    protected fun onUpdate(){
        updated = LocalDateTime.now()
    }

    fun disable() {
        this.enabled = false
    }

    fun enable() {
        this.enabled = true
    }

    fun isEnalbe() : Boolean{
        return enabled
    }
}