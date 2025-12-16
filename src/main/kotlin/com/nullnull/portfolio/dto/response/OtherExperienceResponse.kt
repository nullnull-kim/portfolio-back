package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.OtherExperience
import java.time.LocalDate

data class OtherExperienceResponse (
    val title: String,
    val role: String ? = null,
    val startedAt: LocalDate,
    val endedAt: LocalDate? = null,
    val description: String ? = null,
) {
    companion object {
        fun toResponse(otherExperience: OtherExperience) : OtherExperienceResponse =
            OtherExperienceResponse(
                title = otherExperience.title,
                role = otherExperience.role,
                startedAt = otherExperience.started,
                endedAt = otherExperience.ended,
                description = otherExperience.description
            )
    }
}