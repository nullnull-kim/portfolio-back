package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.YearMonth

@Entity
class Education(
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val profile: Profile,

    @Column(nullable = false)
    val schoolName: String,

    val startedAt: YearMonth,

    val endedAt: YearMonth,

    val location: String,

    ) : BaseEntity() {
}