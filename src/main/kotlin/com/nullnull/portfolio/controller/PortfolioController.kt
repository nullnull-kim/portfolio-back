package com.nullnull.portfolio.controller

import com.nullnull.portfolio.dto.response.OtherExperienceResponse
import com.nullnull.portfolio.dto.mapper.toResponse
import com.nullnull.portfolio.dto.response.CertificationResponse
import com.nullnull.portfolio.dto.response.EducationResponse
import com.nullnull.portfolio.service.*
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

    @GetMapping("/{profileId}/certifications")
    fun getCertifications(@PathVariable profileId: Long) : List<CertificationResponse> =
        certificationService.getAllByProfile(profileId)
            .map { it -> it.toResponse() }

    @GetMapping("/{profileId}/otherExperiences")
    fun getOtherExperiences(@PathVariable profileId: Long) : List<OtherExperienceResponse> =
        otherExperienceService.getAllByProfile(profileId)
            .map { it -> it.toResponse() }
}