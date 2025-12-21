package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.WorkExperience
import java.time.LocalDate

data class WorkExperienceResponse(
    var companyName: String,
    var companyHeadline: String? = null,
    var title: String,
    var startDate: LocalDate,
    var endDate: LocalDate ? = null,
    var isCurrent: Boolean = false,
    var location: String? = null,
    var techStack: String? = null,
    var summary: String? = null,
    var achievements: String? = null,
    var sortOrder: Int? = null,
) {
    companion object {
        fun toResponse(workExperience: WorkExperience): WorkExperienceResponse =
            WorkExperienceResponse(
                companyName = workExperience.companyName,
                companyHeadline = workExperience.companyHeadline,
                title = workExperience.title,
                startDate = workExperience.startDate,
                endDate = workExperience.endDate,
                isCurrent = workExperience.isCurrent,
                location = workExperience.location,
                techStack = workExperience.techStack,
                summary = workExperience.summary,
                achievements = workExperience.achievements,
                sortOrder = workExperience.sortOrder,
            )
    }
}
