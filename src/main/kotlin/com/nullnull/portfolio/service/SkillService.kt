package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Skill
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.SkillRepository
import org.springframework.stereotype.Service

data class SkillCommand(
    val category: String,
    val items: String,
)

@Service
class SkillsService(
    private val skillRepository: SkillRepository,
    private val profileRepository: ProfileRepository
){
    fun create(profileId: Long, skillCommand: SkillCommand) : Skill {
        val profile = profileRepository.findById(profileId)
            .orElseThrow() { IllegalArgumentException("Profile not found [$profileId]") }

        if (profile.enabled == false) {
            throw IllegalArgumentException("Profile is disabled")
        }

        val entity = Skill(
            profile = profile,
            category = skillCommand.category,
            items = skillCommand.items,
        )

        return skillRepository.save(entity)
    }

    fun getAllByProfile(profileId: Long): List<Skill> {

        val profile = profileRepository.findById(profileId)
            .orElseThrow() { IllegalArgumentException("Profile not found [$profileId]") }

        if (profile.enabled == false) {
            throw IllegalArgumentException("Profile is disabled")
        }

        return skillRepository.findAllByProfileAndEnabledTrue(profile)
    }
}