package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileRepository : JpaRepository<Profile, Long> {
    fun findBySlug(key: String): Optional<Profile>
}
