package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class Profile (
    @Column(nullable = false, unique = true)
    val slug: String,

    val name: String,

    val contactEmail: String,

    val github: String? = null,

    val blog: String? = null,

    val birthday: LocalDate,

    val profileImageUrl: String? = null,

    ) : BaseEntity()
{
}