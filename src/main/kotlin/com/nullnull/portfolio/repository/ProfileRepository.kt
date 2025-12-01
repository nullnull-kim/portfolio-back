package com.nullnull.portfolio.repository

import com.nullnull.portfolio.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {

}
