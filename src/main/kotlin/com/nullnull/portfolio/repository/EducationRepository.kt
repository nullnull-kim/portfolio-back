package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface EducationRepository: JpaRepository<Education, Long> {
    fun findAllByProfileAndEnabledTrue(profile: Profile): List<Education>
}
