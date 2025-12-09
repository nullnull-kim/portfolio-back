package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.WorkExperience
import org.springframework.data.jpa.repository.JpaRepository


interface WorkExperienceRepository : JpaRepository<WorkExperience, Long> {
    fun findAllByProfileAndEnabledTrue(profile: Profile) : List<WorkExperience>
}
