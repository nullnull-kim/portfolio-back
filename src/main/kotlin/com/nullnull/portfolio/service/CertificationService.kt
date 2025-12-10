package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Certification
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.CertificationRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

data class CreateCertificationCommand(
    val name: String,
    val issuedBy: String,
    val issuedAt: LocalDate
)

@Service
class CertificationService(
    private val profileRepository: ProfileRepository,
    private val certificationRepository: CertificationRepository,
) {
    fun create(profileId: Long, createCommand: CreateCertificationCommand): Certification {
        val profile = profileRepository.findById(profileId)
            .orElseThrow {
                IllegalArgumentException("Profile Not Found Id [$profileId]")
            }

        if(profile.enabled == false) {
            throw IllegalArgumentException("Profile Disabled")
        }

        val certification = Certification(
            profile = profile,
            name = createCommand.name,
            issuedBy = createCommand.issuedBy,
            issuedAt = createCommand.issuedAt,
        )

        return certificationRepository.save(certification)
    }

    fun getAllByProfile(profileId: Long): List<Certification> {
        val profile = profileRepository.findById(profileId)
            .orElseThrow{
                IllegalArgumentException("Profile Not Found Id [$profileId]")
            }

        if(profile.enabled == false) {
            throw IllegalArgumentException("Profile Disabled")
        }

        return certificationRepository.findAllByProfileAndEnabledTrue(profile)
    }
}