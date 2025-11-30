package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class Education(
    @Column(nullable = false)
    val schoolName: String,

    val startedAt: LocalDate,

    val endedAt: LocalDate,

    val location: String,

) : BaseEntity() {
}