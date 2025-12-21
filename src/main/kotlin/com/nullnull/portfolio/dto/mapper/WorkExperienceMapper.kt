package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.WorkExperience
import com.nullnull.portfolio.dto.response.WorkExperienceResponse

fun WorkExperience.toResponse() : WorkExperienceResponse = WorkExperienceResponse.toResponse(this)