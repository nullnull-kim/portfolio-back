package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.Profile
import java.time.LocalDate

data class ProfileResponse(
    val id: Long,
    val slug: String,
    val name: String,
    val contactEmail: String,
    val github: String? = null,
    val blog: String? = null,
    val birthday: LocalDate,
    val profileImageUrl: String? = null,
){
    companion object{
        fun from(profile: Profile): ProfileResponse = ProfileResponse(
            id = profile.id!!,
            slug = profile.slug,
            name = profile.name,
            contactEmail = profile.contactEmail,
            github = profile.github,
            blog = profile.blog,
            birthday = profile.birthday,
            profileImageUrl = profile.profileImageUrl
        )
    }
}