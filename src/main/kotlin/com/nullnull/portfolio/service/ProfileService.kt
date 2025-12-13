package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.ProfileRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

data class CreateProfileCommand(
    val slug: String,
    val name: String,
    val contactEmail: String,
    val github: String? = null,
    val blog: String? = null,
    val birthday: LocalDate,
    val profileImageUrl: String? = null,
)

@Service
class ProfileService(
    private val profileRepository : ProfileRepository
){
    fun create(command: CreateProfileCommand): Profile {
        val entity = Profile(
            slug = "my",
            name = command.name,
            contactEmail = command.contactEmail,
            github = command.github,
            blog = command.blog,
            birthday = command.birthday,
            profileImageUrl = command.profileImageUrl
        )
        return profileRepository.save(entity)
    }

    fun getByProfileId(profileId: Long): Profile {
        val profile = profileRepository.findById(profileId)
            .orElseThrow() { IllegalArgumentException("Profile not found [$profileId]") }

        if(profile.isEnalbe() == false) {
            throw IllegalArgumentException("profile is disabled")
        }

        return profile
    }

    fun getBySlug(key: String): Profile {
        val profile = profileRepository.findBySlug(key)
            .orElseThrow() { IllegalArgumentException("key not found [$key]") }

        if(profile.isEnalbe() == false) {
            throw IllegalArgumentException("profile is disabled")
        }

        return profile
    }

}