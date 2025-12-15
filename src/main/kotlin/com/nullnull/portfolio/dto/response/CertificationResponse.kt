package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.Certification
import java.time.LocalDate

data class CertificationResponse(
    val name: String,
    val issuedBy: String,
    val issuedAt: LocalDate,
){
    companion object{
        fun toResponse(certification: Certification): CertificationResponse =
            CertificationResponse(
                name = certification.name,
                issuedBy = certification.issuedBy,
                issuedAt = certification.issuedAt,
            )
    }
}