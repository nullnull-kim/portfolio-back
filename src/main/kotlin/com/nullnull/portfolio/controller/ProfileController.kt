package com.nullnull.portfolio.controller

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.dto.mapper.toResponse
import com.nullnull.portfolio.dto.response.ProfileResponse
import com.nullnull.portfolio.service.ProfileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profile")
class ProfileController(
    val profileService: ProfileService
){
    @GetMapping("/{key}")
    fun getProfile(@PathVariable key: String): ProfileResponse = profileService.getBySlug(key).toResponse()
}
