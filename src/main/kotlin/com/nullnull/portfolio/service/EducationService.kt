package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.repository.EducationRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.springframework.stereotype.Service

data class CreateEducationCommand(
    val schoolName: String,
    val startedAt: String,
    val endedAt: String,
    val major: String,
)
@Service
class EducationService(
    private val profileRepository: ProfileRepository,
    private val educationRepository: EducationRepository
) {
    fun create(profileId: Long, command: CreateEducationCommand): Education {
        val profile = profileRepository.findById(profileId)
            .orElseThrow{
                IllegalArgumentException("Profile Not Found Id [$profileId]")
            }

        if(profile.isEnalbe() == false) {
            throw IllegalArgumentException("Profile Disabled")
        }

        val education = Education(
            profile = profile,
            schoolName = command.schoolName,
            startDate = command.startedAt,
            endDate = command.endedAt,
            major = command.major,
        )

        return educationRepository.save(education)
    }

    fun getAllByProfile(profileId: Long): List<Education> {
        val profile = profileRepository.findById(profileId)
            .orElseThrow{
                IllegalArgumentException("Profile Not Found Id [$profileId]")
            }

        if(profile.isEnalbe() == false) {
            throw IllegalArgumentException("Profile Disabled")
        }

        return educationRepository.findAllByProfileAndEnabledTrue(profile)
    }
}