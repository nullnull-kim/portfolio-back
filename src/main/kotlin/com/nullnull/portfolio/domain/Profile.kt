package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Profile (
    @Column(nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val summary: String,

    val contactEmail: String,

    val github: String? = null,

    val blog: String? = null,

    val profileImageUrl: String? = null,

) : BaseEntity()
{
}