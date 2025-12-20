package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.Education

data class EducationResponse (
    val schoolName: String,
    val startDate: String,
    val endDate: String,
    val major: String,
    ){
    companion object{
        fun toResponse(education: Education):EducationResponse = EducationResponse(
            schoolName = education.schoolName,
            startDate = education.startDate,
            endDate = education.endDate,
            major = education.major,
        )
    }
}