package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Skills (
    @Column(nullable = false)
    val category: String,

) : BaseEntity() {
}