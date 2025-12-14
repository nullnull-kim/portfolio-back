package com.nullnull.portfolio.controller

import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.dto.mapper.toResponse
import com.nullnull.portfolio.dto.response.EducationResponse
import com.nullnull.portfolio.service.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/portfolio")
class PortfolioController(
    val certificationService: CertificationService,
    val educationService: EducationService,
    val otherExperienceService: OtherExperienceService,
    val skillsService: SkillsService,
    val workExperienceService: WorkExperienceService
) {
    @GetMapping("/{profileId}/educations")
    fun getEducations(@PathVariable profileId: Long) : List<EducationResponse> =
        educationService.getAllByProfile(profileId)
            .map { it -> it.toResponse() }
}