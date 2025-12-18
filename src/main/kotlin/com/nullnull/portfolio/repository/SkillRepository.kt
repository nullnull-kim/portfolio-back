package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long> {
    fun findAllByProfileAndEnabledTrue(profile: Profile) : List<Skill>

}
