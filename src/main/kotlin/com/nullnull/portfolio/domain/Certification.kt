package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Certification (
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val profile: Profile,

    @Column(nullable = false)
    val name: String,

    val issuedBy: String,

    val issuedDate: LocalDate,

    ) : BaseEntity(){
}