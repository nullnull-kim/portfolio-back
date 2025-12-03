package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class OtherExperience(
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val profile: Profile,

    @Column(nullable = false)
    val title: String,

    val role: String ? = null,

    val startedAt: LocalDate,

    val endedAt: LocalDate ? = null,

    @Column(columnDefinition = "TEXT")
    val description: String ? = null,
) : BaseEntity()
{

}