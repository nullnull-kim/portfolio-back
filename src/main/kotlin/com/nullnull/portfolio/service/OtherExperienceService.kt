package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.OtherExperience
import com.nullnull.portfolio.repository.OtherExperienceRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

data class CreateOtherExperienceCommand(
    val title: String,
    val role: String ? = null,
    val startedAt: LocalDate,
    val endedAt: LocalDate? = null,
    val description: String ? = null,
)

@Service
class OtherExperienceService(
    private val profileRepository: ProfileRepository,
    private val otherExperienceRepository: OtherExperienceRepository,
){
    fun create(profileId: Long, command: CreateOtherExperienceCommand) : OtherExperience {

        val profile = profileRepository.findById(profileId)
            .orElseThrow { IllegalArgumentException("Profile Not Found Id [$profileId]") }

        if(profile.enabled == false) {
            throw IllegalArgumentException("Profile Not Enabled")
        }

        val entity = OtherExperience(
            profile = profile,
            title = command.title,
            role = command.role,
            startDate = command.startedAt,
            endDate = command.endedAt,
            description = command.description,
        )

        return otherExperienceRepository.save(entity)
    }

    fun getAllByProfile(profileId: Long): List<OtherExperience> {
        val profile = profileRepository.findById(profileId)
            .orElseThrow { IllegalArgumentException("Profile Not Found Id [$profileId]") }

        if(profile.enabled == false) {
            throw IllegalArgumentException("Profile Not Enabled")
        }

        return  otherExperienceRepository.findAllByProfileAndEnabledTrue(profile)
    }
}

