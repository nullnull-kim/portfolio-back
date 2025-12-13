package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.dto.response.ProfileResponse

fun Profile.toResponse(): ProfileResponse = ProfileResponse.from(this)