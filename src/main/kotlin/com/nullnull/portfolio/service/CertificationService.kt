package com.nullnull.portfolio.service

import com.nullnull.portfolio.repository.CertificationRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

class CreateCertificationCommand {

}

@Service
class CertificationService(
    private val profileRepository: ProfileRepository,
    private val certificationRepository: CertificationRepository,
){
    fun create(profile: Profile, createCommand: CreateCertificationCommand) {

    }
}