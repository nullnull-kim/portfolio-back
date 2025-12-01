package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.WorkExperience
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.WorkExperienceRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

data class CreateWorkExperienceCommand(
    val companyName: String,
    val companyHeadline: String? = null,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate? = null,
    val isCurrent: Boolean = false,
    val location: String? = null,
    val techStack: String? = null,
    val summary: String? = null,
    val achievements: String? = null,
    val sortOrder: Int? = null,
)

@Service
class WorkExperienceService(
    private val workExperienceRepository: WorkExperienceRepository,
    private val profileRepository: ProfileRepository,
){
    fun getAllByProfile(profileId:Long): List<WorkExperience> {
        val profile = profileRepository.findById(profileId)
            .orElseThrow { IllegalArgumentException("Introduction not found: $profileId") }

        return workExperienceRepository.findAllByProfile(profile)
    }

    fun create(profileId: Long, command: CreateWorkExperienceCommand): WorkExperience {
        val profile = profileRepository.findById(profileId)
            .orElseThrow { IllegalArgumentException("Introduction not found: $profileId") }

        val entity = WorkExperience(
            profile = profile,
            companyName = command.companyName,
            companyHeadline = command.companyHeadline,
            title = command.title,
            startDate = command.startDate,
            endDate = command.endDate,
            isCurrent = command.isCurrent,
            location = command.location,
            techStack = command.techStack,
            summary = command.summary,
            achievements = command.achievements,
            sortOrder = command.sortOrder,
        )

        return workExperienceRepository.save(entity)
    }
}
