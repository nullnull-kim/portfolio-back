package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.Education
import jakarta.persistence.Column

data class EducationResponse (
    val schoolName: String,
    val started: String,
    val ended: String,
    val major: String,
    ){
    companion object{
        fun toResponse(education: Education):EducationResponse = EducationResponse(
            schoolName = education.schoolName,
            started = education.started,
            ended = education.ended,
            major = education.major,
        )
    }
}