package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.OtherExperience
import com.nullnull.portfolio.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface OtherExperienceRepository : JpaRepository<OtherExperience, Long> {
    fun findAllByProfileAndEnabledTrue(profile: Profile) : List<OtherExperience>

}
