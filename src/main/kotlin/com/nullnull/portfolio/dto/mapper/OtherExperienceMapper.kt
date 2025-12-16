package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.OtherExperience
import com.nullnull.portfolio.dto.response.OtherExperienceResponse

fun OtherExperience.toResponse() : OtherExperienceResponse = OtherExperienceResponse.toResponse(this)