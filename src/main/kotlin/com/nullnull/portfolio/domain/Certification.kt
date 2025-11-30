package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class Certification (
    @Column(nullable = false)
    val name: String,

    val issuedBy: String,

    val issuedAt: LocalDate,

    ) : BaseEntity(){
}