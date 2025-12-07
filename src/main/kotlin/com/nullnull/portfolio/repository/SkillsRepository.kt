package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.Skills
import org.springframework.data.jpa.repository.JpaRepository

interface SkillsRepository : JpaRepository<Skills, Long> {
    fun findAllByProfileAndIsEnabledTrue(profile: Profile) : List<Skills>

}
