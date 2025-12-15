package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.Certification
import com.nullnull.portfolio.dto.response.CertificationResponse

fun Certification.toResponse() : CertificationResponse = CertificationResponse.toResponse(this)