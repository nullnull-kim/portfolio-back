package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class OtherExperience(
    @Column(nullable = false)
    val title: String,

    val role: String,

    val startedAt: LocalDateTime,

    val endedAt: LocalDate ? = null,

    @Column(columnDefinition = "TEXT")
    val description: String ? = null,
) : BaseEntity()
{

}