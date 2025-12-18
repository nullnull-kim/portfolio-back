package com.nullnull.portfolio.dto.mapper

import com.nullnull.portfolio.domain.Skill
import com.nullnull.portfolio.dto.response.SkillResponse

fun Skill.toResponse() : SkillResponse = SkillResponse.toResponse(this)