package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.dto.response.EducationResponse

fun Education.toResponse(): EducationResponse = EducationResponse.toResponse(this)