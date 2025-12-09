package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Certification
import com.nullnull.portfolio.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface CertificationRepository: JpaRepository<Certification, Long> {
    fun findAllByProfileAndEnabledTrue(profile: Profile): List<Certification>
}
