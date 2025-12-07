package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Skills
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.SkillsRepository
import org.springframework.stereotype.Service

data class SkillsCommand(
    val category: String,
    val items: String,
)

@Service
class SkillsService(
    private val skillsRepository: SkillsRepository,
    private val profileRepository: ProfileRepository
){
    fun create(profileId: Long, skillsCommand: SkillsCommand) : Skills {
        val profile = profileRepository.findById(profileId)
            .orElseThrow() { IllegalArgumentException("Profile not found [$profileId]") }

        if (profile.isEnabled == false) {
            throw IllegalArgumentException("Profile is disabled")
        }

        val entity = Skills(
            profile = profile,
            category = skillsCommand.category,
            items = skillsCommand.items,
        )

        return skillsRepository.save(entity)
    }
}