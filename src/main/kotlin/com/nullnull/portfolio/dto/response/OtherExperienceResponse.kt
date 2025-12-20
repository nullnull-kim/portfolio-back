package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.OtherExperience
import java.time.LocalDate

data class OtherExperienceResponse (
    val title: String,
    val role: String ? = null,
    val startDate: LocalDate,
    val endDate: LocalDate? = null,
    val description: String ? = null,
) {
    companion object {
        fun toResponse(otherExperience: OtherExperience) : OtherExperienceResponse =
            OtherExperienceResponse(
                title = otherExperience.title,
                role = otherExperience.role,
                startDate = otherExperience.startDate,
                endDate = otherExperience.endDate,
                description = otherExperience.description
            )
    }
}