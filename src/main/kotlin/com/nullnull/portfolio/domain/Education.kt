package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
class Education(
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val profile: Profile,

    @Column(nullable = false)
    val schoolName: String,

    @Column(nullable = false, length = 7)
    val startDate: String,

    @Column(nullable = false, length = 7)
    val endDate: String,

    val major: String,

    ) : BaseEntity() {
}